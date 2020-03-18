package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.luismanzanero.kotlinnodejsstarter.adapters.UsersAdapter
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var adapter =
        UsersAdapter(
            this
        )
    var page = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        usersRecyclerView.layoutManager =  layoutManager
        usersRecyclerView.adapter = adapter
        swipeRefresh.setOnRefreshListener {
            page = 0
            getPosts(page)
            adapter.usersList.removeAll(adapter.usersList)
            swipeRefresh.isRefreshing = false
        }
        runOnUiThread {
            getPosts(page)
        }

        usersRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = usersRecyclerView.layoutManager!!.childCount
                val totalItemCount = usersRecyclerView.layoutManager!!.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
//                if(visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0){
//                   getPosts(page)
//                    println("Loading new page")
//                }
                println("Total Item Count: $totalItemCount and last visible item: $lastVisibleItemPosition")
                if(lastVisibleItemPosition + 1 == totalItemCount){
                    getPosts(page)
                }
            }
        })
    }

    private fun getPosts(currentPage: Int){
        progressBar2.visibility = View.VISIBLE
        Fuel.get("https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc&page=$currentPage&per_page=10")
            .responseString { request, response, result ->
                val usersData = result.get()
                val data = Gson().fromJson(usersData, Users::class.java)

                adapter.addData(data)
                adapter.notifyDataSetChanged()
                progressBar2.visibility = View.GONE
                page++
            }
        println("Current Page: $page")
    }
}

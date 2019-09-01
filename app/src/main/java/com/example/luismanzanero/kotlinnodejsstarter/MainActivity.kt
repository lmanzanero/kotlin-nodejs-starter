package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var adapter = UsersAdapter()
    var page = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        usersRecyclerView.layoutManager =  layoutManager
        usersRecyclerView.adapter = adapter
        runOnUiThread {
            getPosts(page)
        }

        usersRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = usersRecyclerView.layoutManager!!.childCount
                val totalItemCount = usersRecyclerView.layoutManager!!.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount){
//                   getPosts(page)
                    println("Loading new page")
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
            }
        page++
        println("Current Page: $page")
    }
}

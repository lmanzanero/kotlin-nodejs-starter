package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var page = 0
    var adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = adapter

        mainBtn.setOnClickListener {

            runOnUiThread {
                getPosts(page)
            }
            page++

            println("Page number: $page")
        }
    }

    private fun getPosts(page: Int){
        progressBar2.visibility = View.VISIBLE
        Fuel.get("https://api.github.com/users?since=$page&per_page=10")
            .responseString { request, response, result ->
                val usersData = result.get()
                val data: MutableList<Users> = Gson().fromJson(usersData, Array<Users>::class.java).toMutableList()

                adapter.addData(data)
                adapter.notifyDataSetChanged()
                progressBar2.visibility = View.GONE
            }

    }
}

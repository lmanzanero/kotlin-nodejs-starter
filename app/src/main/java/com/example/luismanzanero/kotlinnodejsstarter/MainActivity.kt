package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBtn.setOnClickListener {
            getPosts()
        }
    }

    fun getPosts(){
        Fuel.get("https://api.github.com/users?since=1&per_page=10")
            .responseString { request, response, result ->
                val usersData = result.get()
                val data: List<Users> = Gson().fromJson(usersData, Array<Users>::class.java).toList()
                data.forEach {
                    println(it.login)
                }
            }

    }
}

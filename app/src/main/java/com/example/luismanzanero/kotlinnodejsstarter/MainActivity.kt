package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.Fuel
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
        Fuel.get("https://jsonplaceholder.typicode.com/posts")
            .responseString { request, response, result ->
                println(result.get())
            }

    }
}

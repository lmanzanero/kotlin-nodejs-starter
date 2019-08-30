package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.users_row.view.*

class UsersAdapter : RecyclerView.Adapter<ViewHolder>() {

    var usersList: MutableList<Users> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.users_row, parent, false)
        return ViewHolder(cellRow)
    }

    override fun getItemCount(): Int {
        println("From Item Count: ${usersList.size}")
        return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = usersList.get(position)
        holder.view.usersButton.text = data.login
    }

    fun addData(listItems: MutableList<Users>){
        var size = usersList.size
        usersList.addAll(listItems)
        var newSize = usersList.size
        notifyDataSetChanged()
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val buttonName = view.usersButton.text
}
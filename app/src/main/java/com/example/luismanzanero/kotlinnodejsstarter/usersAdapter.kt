package com.example.luismanzanero.kotlinnodejsstarter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.users_row.view.*

class UsersAdapter : RecyclerView.Adapter<ViewHolder>() {

    var usersList: MutableList<Items> = ArrayList()
    var usersId: MutableList<Users> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.users_row, parent, false)
        return ViewHolder(cellRow)
    }

    override fun getItemCount(): Int {
        println("UserList Size: ${usersList.size}")
        return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = usersList.get(position)
        var items = usersList.get(position)
        holder.buttonName?.text = data.name
        holder.itemsName?.text = items.owner.login
    }

    fun addData(listItems: Users){
        var size = usersList.size
        usersList.addAll(listItems.items)

        listItems.items.forEach {
            println(it.owner.login)
        }

        var newSize = usersList.size
        notifyItemRangeChanged(size, newSize)
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val buttonName = view.total_count
    val itemsName = view.items_name
}
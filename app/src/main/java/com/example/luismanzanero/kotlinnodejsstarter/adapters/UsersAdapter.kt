package com.example.luismanzanero.kotlinnodejsstarter.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.luismanzanero.kotlinnodejsstarter.ui.data.Items
import com.example.luismanzanero.kotlinnodejsstarter.R
import com.example.luismanzanero.kotlinnodejsstarter.ui.UserScreen
import com.example.luismanzanero.kotlinnodejsstarter.ui.data.Users
import kotlinx.android.synthetic.main.users_row.view.*

class UsersAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    var usersList: MutableList<Items> = ArrayList()
    var usersId: MutableList<Users> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.users_row, parent, false)
        return ViewHolder(
            cellRow
        )
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

        //Add click listener to each card item
        holder.view.cards_row.setOnClickListener {
            Toast.makeText(context,"${data.name}",Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context, UserScreen::class.java)
            intent.putExtra("data", data.name)
            startActivity(this.context, intent, null)

        }
    }

    fun addData(listItems: Users){
        usersList.addAll(listItems.items)
        listItems.items.forEach {
            println(it.owner.login)
        }
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val buttonName = view.total_count
    val itemsName = view.items_name
}
package com.aeharake.choters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aeharake.choters.R
import com.aeharake.choters.room.entities.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    var users: List<User>? = null

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var lastMessage: TextView = itemView.findViewById(R.id.tv_last_message)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_contact, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        users?.let {
            return it.size
        } ?: return 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users!![position]
        holder.name.text = "${user.firstName} ${user.lastName}"
    }
}
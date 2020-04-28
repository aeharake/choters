package github.aeharake.choters.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.aeharake.choters.R
import github.aeharake.choters.room.entities.UserMessage
import github.aeharake.choters.utils.CalendarHelper
import java.text.SimpleDateFormat
import java.util.*

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    var userMessages: List<UserMessage>? = null

    interface OnUserClickListener {
        fun onClick(user: UserMessage)
    }

    private var onUserClickListener: OnUserClickListener? = null
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var lastMessage: TextView = itemView.findViewById(R.id.tv_last_message)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_contact, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        userMessages?.let {
            return it.size
        } ?: return 0
    }

    fun setOnUserClickListener(onUserClickListener: OnUserClickListener) {
        this.onUserClickListener = onUserClickListener
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userMessages!![position]
        holder.name.text = user.user!!.getFullName()
        if (user.message != null) {
            if (!TextUtils.isEmpty(user.message!!.message)) {
                holder.lastMessage.text = user.message!!.message
                holder.lastMessage.visibility = View.VISIBLE
                holder.tvTime.text = CalendarHelper.getPrettyTime(user.message!!.createdOn)
                holder.tvTime.visibility = View.VISIBLE
            } else {
                holder.lastMessage.text = ""
                holder.lastMessage.visibility = View.GONE
                holder.tvTime.text = ""
                holder.tvTime.visibility = View.GONE
            }

        } else {
            holder.lastMessage.text = ""
            holder.lastMessage.visibility = View.GONE
            holder.tvTime.text = ""
            holder.tvTime.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onUserClickListener?.onClick(user)
        }
    }
}
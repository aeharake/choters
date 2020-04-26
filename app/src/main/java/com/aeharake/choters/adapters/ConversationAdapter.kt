package com.aeharake.choters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aeharake.choters.R
import com.aeharake.choters.room.entities.Message
import java.lang.IllegalStateException

class ConversationAdapter : RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {

    companion object {
        const val VIEW_TYPE_ME = 1
        const val VIEW_TYPE_FRIEND = 2
    }

    var messages: List<Message>? = null

    inner class ConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMessage: TextView = itemView.findViewById(R.id.tv_message)
    }

    private fun inflate(parent: ViewGroup, resId: Int): View {
        return LayoutInflater.from(parent.context).inflate(resId, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        var view: View? = null
        when (viewType) {
            VIEW_TYPE_ME -> {
                view = inflate(parent, R.layout.item_message_me)
            }
            VIEW_TYPE_FRIEND -> {
                view = inflate(parent, R.layout.item_message_friend)
            }
        }
        if(view == null){
            throw IllegalStateException("View cannot be null! Problem inflating resource  #$viewType")
        }
        return ConversationViewHolder(view)
    }

    override fun getItemCount(): Int {
        messages?.let {
            return it.size
        } ?: return 0
    }

    override fun getItemViewType(position: Int): Int {
        messages!![position].sender?.let {
            return VIEW_TYPE_FRIEND
        } ?: kotlin.run {
            return VIEW_TYPE_ME
        }
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val message = messages!![position]
        holder.tvMessage.text = message.message
    }
}
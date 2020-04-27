package com.aeharake.choters.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aeharake.choters.viewmodels.ConversationViewModel
import com.aeharake.choters.activities.MainActivity.Companion.USER_ID
import com.aeharake.choters.activities.MainActivity.Companion.USER_NAME
import com.aeharake.choters.R
import com.aeharake.choters.adapters.ConversationAdapter
import com.aeharake.choters.ui.SendTextView

class ConversationActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var sendTextView: SendTextView
    private lateinit var conversationViewModel: ConversationViewModel
    private var userId: Int? = null
    private val adapter = ConversationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        conversationViewModel = ViewModelProvider(this).get(ConversationViewModel::class.java)
        setContentView(R.layout.activity_conversation)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        initViews()
        intent?.extras?.apply {
            getString(USER_ID)?.let { id ->
                userId = id.toInt()
            } ?: kotlin.run {
                throw IllegalStateException("You didn't pass an ID!")
            }
            getString(USER_NAME)?.let {
                title = it
            } ?: kotlin.run {
                title = "N/A"
            }
        }

        conversationViewModel.getAllMessage(userId!!)
            .observe(this, androidx.lifecycle.Observer { msgs ->
                adapter.messages = msgs
                rv.adapter?.notifyDataSetChanged() //we should pay attention to diffing later on

            })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun initViews() {
        rv = findViewById(R.id.rv_conversation)
        rv.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        rv.adapter = adapter.apply {
            registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
                override fun onChanged() {
                    super.onChanged()
                    rv.smoothScrollToPosition(adapter.itemCount)
                }
            })
        }
        sendTextView = findViewById(R.id.stv)
        sendTextView.setOnSendClickListener(object : SendTextView.OnSendClickListener {
            override fun onSendClick(text: String) {
                conversationViewModel.insertAndEcho(text, userId!!) // it's ok to ignore the null warning here, because i'm throwing an exception above if userId is not specified by developer. So userid will always be there.
            }
        })
        sendTextView.setFocused()
    }
}
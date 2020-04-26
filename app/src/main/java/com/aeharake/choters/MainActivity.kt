package com.aeharake.choters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aeharake.choters.adapters.UsersAdapter
import com.aeharake.choters.room.entities.User


class MainActivity : AppCompatActivity() {

    companion object {
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var usersViewModel: UsersViewModel
    private val adapter: UsersAdapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        initViews()
        usersViewModel.populate()
        usersViewModel.users.observe(this,
            Observer { users ->
                adapter.users = users
                recyclerView.adapter?.notifyDataSetChanged()
            })
        adapter.setOnUserClickListener(object : UsersAdapter.OnUserClickListener {
            override fun onClick(user: User) {
                val intent = Intent(this@MainActivity, ConversationActivity::class.java)
                intent.putExtra(USER_ID, user.id.toString())
                intent.putExtra(USER_NAME, user.getFullName())
                startActivity(intent)
            }
        })
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rv_friends)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter
    }
}

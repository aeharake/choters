package com.aeharake.choters

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aeharake.choters.adapters.UsersAdapter


class MainActivity : AppCompatActivity() {

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
                Toast.makeText(
                    this@MainActivity,
                    "Data changed, new size: ${users?.size}",
                    Toast.LENGTH_LONG
                ).show()
            })
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rv_friends)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter
    }
}

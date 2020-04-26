package com.aeharake.choters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.aeharake.choters.MainActivity.Companion.USER_ID
import com.aeharake.choters.MainActivity.Companion.USER_NAME
import java.lang.IllegalStateException

class ConversationActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    var userId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
        initViews()
        intent?.extras?.apply {
            getString(USER_ID)?.let { id ->
                userId = id.toInt()
            } ?: kotlin.run {
                throw IllegalStateException("You didn't pass an ID!")
            }
            getString(USER_NAME)?.let {
                title = it
            }?: kotlin.run {
                title = "N/A"
            }
        }
    }

    private fun initViews() {
        rv = findViewById(R.id.rv_conversation)
    }
}
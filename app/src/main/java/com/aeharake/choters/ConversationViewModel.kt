package com.aeharake.choters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aeharake.choters.repos.MessageRepository
import com.aeharake.choters.room.entities.Message

class ConversationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MessageRepository = MessageRepository(application)

    fun getAllMessage(id: Int): LiveData<List<Message>> {
        return repository.getAllMessages(id)
    }

    fun insertAndEcho(text: String,id: Int){
        repository.insertMessageAndEcho(text, id)

        //we shouldn't echo anything here, because insert is async, so we should insert and echo in background
    }

}
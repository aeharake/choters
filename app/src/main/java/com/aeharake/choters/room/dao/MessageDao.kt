package com.aeharake.choters.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aeharake.choters.room.entities.Message

@Dao
interface MessageDao {

    @Insert
    fun insert(message: Message)

    @Query("SELECT * FROM message WHERE (sender = :id AND recipient is null) OR (sender is null AND recipient = :id) ORDER BY created_on")
    fun getAllMessagesAsync(id: Int): LiveData<List<Message>>

    @Query("SELECT * FROM message where sender != :id")
    fun getAllMessages(id: Int): List<Message>

}
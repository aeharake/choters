package com.aeharake.choters.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aeharake.choters.room.entities.Message
import com.aeharake.choters.room.entities.User

@Dao
interface MessageDao {

    @Insert
    fun insert(message: Message)

//    @Query("SELECT * FROM message WHERE sender = :user ORDER BY created_on DESC LIMIT 1")
//    fun getLastMessage(user: User) : Message
//
//    @Query("SELECT message FROM message WHERE sender = :user ORDER BY created_on")
//    fun getAllMessages(user: User): LiveData<List<Message>>

}
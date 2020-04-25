package com.aeharake.choters.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aeharake.choters.room.entities.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Insert
    fun insert(vararg user: User)

    @Query("SELECT u.* FROM user as u INNER JOIN message as m on u.id = m.sender ORDER BY m.created_on DESC")
    fun getAllUsers() : LiveData<List<User>>

    @Query("DELETE FROM user")
    fun deleteUsers()
}
package com.aeharake.choters.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import com.aeharake.choters.room.entities.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Insert
    fun insert(vararg user: User)

    @Insert
    fun insert(user: List<User>)


    //UNION ALL SELECT u.* from user as u  JOIN message as m on u.id = m.sender
//    @Query("SELECT u.* from user as u")
    @Query("SELECT u.*,m.message FROM user as u LEFT JOIN message as m on u.id = m.sender  ORDER BY m.created_on DESC")
    fun getAllUsersAsync() : LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>?

    @Query("DELETE FROM user")
    fun deleteUsers()
}
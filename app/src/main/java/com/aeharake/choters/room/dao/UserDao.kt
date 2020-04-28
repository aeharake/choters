package com.aeharake.choters.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import com.aeharake.choters.room.entities.User
import com.aeharake.choters.room.entities.UserMessage

@Dao
interface UserDao : BaseDao{

    @Insert
    fun insert(user: User)

    @Insert
    fun insert(user: List<User>)

    @Query("SELECT u.*,m.* FROM user as u LEFT JOIN message as m on u.user_id = m.sender group by u.user_id ORDER BY max(m.created_on) DESC")
    fun getAllUsersAsync() : LiveData<List<UserMessage>>

    @Query("SELECT count(*) FROM user")
    fun getUsersCount(): Int

    @Query("DELETE FROM user")
    fun deleteUsers()
}
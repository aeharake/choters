package com.aeharake.choters.repos

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import com.aeharake.choters.mocker.UsersGenerator
import com.aeharake.choters.room.dao.UserDao
import com.aeharake.choters.room.entities.User
import com.aeharake.choters.room.entities.UserMessage

class UserRepository(application: Application) : BaseRepository<UserDao>(application) {
    private val handlerThread: HandlerThread = HandlerThread("database_populator")

    fun getAllUsers(): LiveData<List<UserMessage>> {
        return dao.getAllUsersAsync()
    }

    override fun createDao() : UserDao {
        return database.userDao()
    }

    fun populate() {
        handlerThread.start().apply {
            Handler(handlerThread.looper).post {
                val size = database.userDao().getUsersCount()
                if (size == 0) {
                    UsersGenerator.getRandomFullNames()
                        .map {
                            User(it.firstName, it.lastName)
                        }
                        .let { users ->
                            dao.insert(users)
                        }
                }

            }
        }
    }

}
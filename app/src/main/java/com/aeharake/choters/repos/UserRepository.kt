package com.aeharake.choters.repos

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import com.aeharake.choters.mocker.UsersGenerator
import com.aeharake.choters.room.entities.User

class UserRepository(application: Application) : ParentRepository(application) {
    private val handlerThread: HandlerThread = HandlerThread("database_populator")
    private val userDao = database.userDao()

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsersAsync()
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
                            userDao.insert(users)
                        }
                }

            }
        }
    }
}
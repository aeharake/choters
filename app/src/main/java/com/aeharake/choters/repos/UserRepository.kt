package com.aeharake.choters.repos

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import com.aeharake.choters.mocker.UsersGenerator
import com.aeharake.choters.room.entities.User
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserRepository(application: Application) : ParentRepository(application) {
    private val handlerThread: HandlerThread = HandlerThread("database_populator")
    private val userDao = database.userDao()

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsersAsync()
    }

    fun populate() {
        handlerThread.start().apply {
            Handler(handlerThread.looper).post {
                var size = database.userDao().getAllUsers()?.size
                if (size == null) {
                    size = 0
                }
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
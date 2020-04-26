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

    fun insertUsers(vararg user: User) {
        Observable.fromCallable<Boolean> {
            database.userDao().insert(*user)
            true
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Boolean> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Boolean) {

                }

                override fun onError(e: Throwable) {
                }

            })
    }

    fun getAllUsers(): LiveData<List<User>> {
        return database.userDao().getAllUsersAsync()
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
                        }.
//                        .forEach {
//                            user->
//                            database.userDao().insert(user)
//                        }
                        let { users ->
                            database.userDao().insert(users)
                        }
                }

            }
        }
    }
}
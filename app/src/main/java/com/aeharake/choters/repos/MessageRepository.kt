package com.aeharake.choters.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.aeharake.choters.room.dao.MessageDao
import com.aeharake.choters.room.entities.Message
import com.aeharake.choters.room.entities.User
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MessageRepository(application: Application) : ParentRepository(application) {
    private var messageDao: MessageDao = database.messageDao()

//    fun getAllMessages(user: User): LiveData<List<Message>> {
//        return messageDao.getAllMessages(user)
//    }

    fun insertMessage(message: Message) {
        Observable.fromCallable {
            messageDao.insert(message)
            true
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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

//    fun getLastMessage(user: User): Message {
//        return Observable.fromCallable {
//            return@fromCallable messageDao.getLastMessage(user)
//        }.observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .blockingFirst()
//    }
//
//    fun getAllMessages(user: User): LiveData<List<Message>> {
//        return messageDao.getAllMessages(user)
////        return Observable.fromCallable {
////            return@fromCallable messageDao.getAllMessages(user)
////        }.observeOn(AndroidSchedulers.mainThread())
////            .subscribeOn(Schedulers.io())
////            .blockingFirst()
//    }
}
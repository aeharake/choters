package com.aeharake.choters.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.aeharake.choters.room.dao.MessageDao
import com.aeharake.choters.room.entities.Message
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import kotlin.random.Random

class MessageRepository(application: Application) : ParentRepository(application) {
    private var messageDao: MessageDao = database.messageDao()

    fun getAllMessages(id: Int): LiveData<List<Message>> {
        return messageDao.getAllMessagesAsync(id)
    }

    fun insertMessageAndEcho(text: String, id: Int) {
        //we don't pass them inside a data object becausee we want 2 different objects that we will construct in background

        Observable.fromCallable {
            val original = Message(text, null, id)
            messageDao.insert(original)
            //there should be here a 0.5 second interval
            val random: Double = Random.nextInt(1, 6) / 10.0;
            Timber.v("Random value generated: $random")
            Thread.sleep((random * 1000).toLong())
            val echoed = Message(original.message, id, null)
            messageDao.insert(echoed)
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
}
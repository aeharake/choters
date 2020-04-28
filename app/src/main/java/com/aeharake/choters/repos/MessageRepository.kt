package com.aeharake.choters.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.aeharake.choters.room.dao.MessageDao
import com.aeharake.choters.room.entities.Message
import com.aeharake.choters.utils.CodeUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import kotlin.random.Random

class MessageRepository(application: Application) : BaseRepository<MessageDao>(application) {

    fun getAllMessages(id: Int): LiveData<List<Message>> {
        return dao.getAllMessagesAsync(id)
    }


    override fun createDao() : MessageDao {
        return database.messageDao()
    }

    fun insertMessageAndEcho(text: String, id: Int) {
        //we don't pass them inside a data object becausee we want 2 different objects that we will construct in background

        Observable.fromCallable {
            val original = Message(text, null, id)
            dao.insert(original)
            //there should be here a 0.5 second interval
            val random: Double = Random.nextInt(1, 6) / 10.0;
            Timber.v("Random value generated: $random")
            Thread.sleep((random * 1000).toLong())
            val echoed = Message(original.message, id, null)
            dao.insert(echoed)
            true
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(CodeUtils.emptySubscriber())
    }

}
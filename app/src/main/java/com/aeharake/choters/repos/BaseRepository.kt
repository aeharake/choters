package com.aeharake.choters.repos

import android.app.Application
import com.aeharake.choters.room.ChatDatabase
import com.aeharake.choters.room.dao.BaseDao
import com.aeharake.choters.room.dao.UserDao

open class BaseRepository<T : BaseDao>(application: Application) {

    var database: ChatDatabase = ChatDatabase.getInstance(application)
    var dao: T = this.createDao()

    open fun createDao()  : T{
        throw NotImplementedError("Method createDao not implemented!")
    }
}

package github.aeharake.choters.repos

import android.app.Application
import github.aeharake.choters.room.ChatDatabase
import github.aeharake.choters.room.dao.BaseDao
import github.aeharake.choters.room.dao.UserDao

open class BaseRepository<T : BaseDao>(application: Application) {

    var database: ChatDatabase = ChatDatabase.getInstance(application)
    var dao: T = this.createDao()

    open fun createDao()  : T{
        throw NotImplementedError("Method createDao not implemented!")
    }
}

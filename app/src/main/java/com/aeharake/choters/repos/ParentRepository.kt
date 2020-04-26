package com.aeharake.choters.repos

import android.app.Application
import com.aeharake.choters.room.ChatDatabase

open class ParentRepository(application: Application) {

    protected var database: ChatDatabase = ChatDatabase.getInstance(application)

}
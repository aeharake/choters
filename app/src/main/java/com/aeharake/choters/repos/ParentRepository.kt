package com.aeharake.choters.repos

import android.app.Application
import com.aeharake.choters.room.entities.ChatDatabase

open class ParentRepository(application: Application) {

    var database: ChatDatabase = ChatDatabase.getInstance(application)

}
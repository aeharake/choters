package com.aeharake.choters.room.entities

import android.content.Context
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aeharake.choters.mocker.UsersGenerator
import com.aeharake.choters.room.dao.MessageDao
import com.aeharake.choters.room.dao.UserDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

@Database(
    entities = [User::class, Message::class],
    version = ChatDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao

    companion object {
        private val DATABASE_NAME = "chat_db.db"
        const val DATABASE_VERSION = 1

        @Volatile
        private var instance: ChatDatabase? = null


        fun getInstance(context: Context): ChatDatabase {
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .let {
                        instance = it
                    }
            }
            return instance!!
        }


        private fun buildDatabase(context: Context): ChatDatabase {

            return Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                DATABASE_NAME
            ).build();
        }
    }


}
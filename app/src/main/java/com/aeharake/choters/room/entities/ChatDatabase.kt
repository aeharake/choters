package com.aeharake.choters.room.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aeharake.choters.room.dao.MessageDao
import com.aeharake.choters.room.dao.UserDao

@Database(entities = [User::class, Message::class], version = ChatDatabase.DATABASE_VERSION)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun messageDao() : MessageDao

    companion object {
        private const val DATABASE_NAME = "chat_db.db"
        const val DATABASE_VERSION = 1

        @Volatile
        private var instance: ChatDatabase? = null

        fun getInstance(context: Context): ChatDatabase {
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).let {
                    instance = it
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context): ChatDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}
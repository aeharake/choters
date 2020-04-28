package github.aeharake.choters.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import github.aeharake.choters.room.dao.MessageDao
import github.aeharake.choters.room.dao.UserDao
import github.aeharake.choters.room.entities.Message
import github.aeharake.choters.room.entities.User

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
                instance
                    ?: buildDatabase(
                        context
                    )
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
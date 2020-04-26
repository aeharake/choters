package com.aeharake.choters.room.entities

import androidx.room.*
@Entity(
    tableName = "user"
)
data class User(
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String

) {
    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "image")
    var image: String? = null

//    var message: String? = null

    fun getFullName(): String {
        return "$firstName $lastName"
    }

}
package com.aeharake.choters.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user",
    indices = [Index(value = ["id"], unique = true)]
)
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null
}

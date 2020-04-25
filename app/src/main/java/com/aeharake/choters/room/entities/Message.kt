package com.aeharake.choters.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "message",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["sender", "recipient"]
    )]
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "sender")
    val sender: Int,
    @ColumnInfo(name = "recipient")
    val recipient: Int,
    @ColumnInfo(name = "created_on")
    val createdOn: String
)
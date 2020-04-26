package com.aeharake.choters.room.entities

import androidx.room.*

@Entity(
    tableName = "message",
    indices = [Index(
        value = ["sender"],
        unique = false
    ),Index(
        value = ["recipient"],
        unique = false
    )],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["sender"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["recipient"]
        )
    ]
)
data class Message(
    @ColumnInfo(name = "message")
    var message: String,
    @ColumnInfo(name = "sender")
    var sender: Int? = null,
    @ColumnInfo(name = "recipient")
    var recipient: Int? = null

    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "status")
    var status: Int? = null //will not use this now because we don't have interaction with a server.

    @ColumnInfo(name = "created_on")
    var createdOn: String = System.currentTimeMillis().toString()
}
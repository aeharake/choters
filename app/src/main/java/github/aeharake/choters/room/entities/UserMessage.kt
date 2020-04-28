package github.aeharake.choters.room.entities

import androidx.room.Embedded

class UserMessage {

    @Embedded
    var user: User? = null

    @Embedded
    var message: Message? = null
}
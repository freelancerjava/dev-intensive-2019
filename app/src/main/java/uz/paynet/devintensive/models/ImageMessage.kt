package uz.paynet.devintensive.models

import uz.paynet.devintensive.extensions.humanazeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String =
        "id:$id ${from?.firstName} ${if (isIncoming) "receive" else "send"}" +
                " image: \"$image\" date: ${date.humanazeDiff()}"
}
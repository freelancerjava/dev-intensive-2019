package uz.paynet.devintensive.extensions

import uz.paynet.devintensive.models.User
import uz.paynet.devintensive.models.UserView
import uz.paynet.devintensive.utils.Utils

fun User.toUserView(): UserView {

    val nickname = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "Never been active" else if (isOnline) "online" else "Last visit ... ${lastVisit?.humanazeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        initials = initials,
        avatar = avatar,
        status = status
    )
}
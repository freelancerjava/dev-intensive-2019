package uz.paynet.devintensive.models

class Chat(
    val id: String,
    val users: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf()
) {
}
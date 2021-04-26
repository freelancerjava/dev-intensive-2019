package uz.paynet.devintensive

import org.junit.Test

import org.junit.Assert.*
import uz.paynet.devintensive.extensions.TimeUnits
import uz.paynet.devintensive.extensions.add
import uz.paynet.devintensive.extensions.format
import uz.paynet.devintensive.extensions.toUserView
import uz.paynet.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Serr", null, lastVisit = Date(), isOnline = true)
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("Salim Alimov1")
        val user2 = User.makeUser("Salim Alimov2")
        val user3 = User.makeUser("Salim Alimov3")
    }

    @Test
    fun test_decomposition() {

        val user = User.makeUser("Alim Salimov")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("Alim Salimov")
        val user2 = user.copy(id = "3", lastVisit = Date().add(-5, TimeUnits.DAY))
        val user3 = user.copy(id = "3", lastVisit = Date().add(2, TimeUnits.HOUR))

        if (user.equals(user2)) {
            print("equals \n ${user.hashCode()} $user \n ${user2.hashCode()} $user2")
        } else {
            print("not equals \n ${user.hashCode()} $user \n${user2.hashCode()} $user2")
        }

        if (user.equals(user2)) {
            print("equals \n ${System.identityHashCode(user)} \n ${System.identityHashCode(user2)}")
        } else {
            print("not equals \n ${System.identityHashCode(user)} \n ${System.identityHashCode(user2)}")
        }

        println()

        println(user.lastVisit?.format())
        println(user2.lastVisit?.format())
        println(user3.lastVisit?.format())
    }

    @Test
    fun test_dataq_mapping(){
        val user = User.makeUser("Alim Salimov")
        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Paynet Java")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("1"), payload = "any image message", type = "image")

        when(imageMessage){
            is BaseMessage -> println("basemessage message")
            is TextMessage -> println("text message")
            is ImageMessage -> println("image message")
        }

        println(txtMessage.formatMessage())
        println(imageMessage.formatMessage())
    }
}
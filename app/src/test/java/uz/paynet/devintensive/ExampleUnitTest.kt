package uz.paynet.devintensive

import org.junit.Test

import org.junit.Assert.*
import uz.paynet.devintensive.extensions.*
import uz.paynet.devintensive.models.*
import uz.paynet.devintensive.utils.Utils
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
    fun test_dataq_mapping() {
        val user = User.makeUser("Макеев Михаил")
        val user2 = user.copy(id = "2", lastVisit = Date().add(-55, TimeUnits.SECOND))
        val user3 = user.copy(id = "3", lastVisit = Date().add(-55, TimeUnits.SECOND))
        val user4 = user.copy(id = "4", lastVisit = Date().add(-55, TimeUnits.SECOND))
        val user5 = user.copy(id = "5", lastVisit = Date().add(-55, TimeUnits.SECOND))
        val user6 = user.copy(id = "6", lastVisit = Date().add(-55, TimeUnits.SECOND))
        val user7 = user.copy(id = "7", lastVisit = Date().add(-55, TimeUnits.SECOND))
        val userView = user.toUserView()
        val user2View = user2.toUserView()
        val user3View = user3.toUserView()
        val user4View = user4.toUserView()
        val user5View = user5.toUserView()
        val user6View = user6.toUserView()

        userView.printMe()
        user2View.printMe()
        user3View.printMe()
        user4View.printMe()
        user5View.printMe()
        user6View.printMe()


    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Paynet Java")
        val txtMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage =
            BaseMessage.makeMessage(user, Chat("1"), payload = "any image message", type = "image")

        when (imageMessage) {
            is BaseMessage -> println("basemessage message")
            is TextMessage -> println("text message")
            is ImageMessage -> println("image message")
        }

        println(txtMessage.formatMessage())
        println(imageMessage.formatMessage())
    }

    @Test
    fun test_strip_html() {
        println("""
            ${"<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml()} //Образовательное IT-сообщество Skill Branch
            ${"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()} //Образовательное IT-сообщество Skill Branch
        """.trimIndent())
    }

    @Test
    fun test_truncate() {
        print("A     ".truncate(3))
    }

    @Test
    fun test_humanize_diff() {
        println(
            """
            ${Date().add(-2, TimeUnits.HOUR).humanizeDiff()} //2 часа назад
            ${Date().add(-5, TimeUnits.DAY).humanizeDiff()} //5 дней назад
            ${Date().add(2, TimeUnits.MINUTE).humanizeDiff()} //через 2 минуты
            ${Date().add(7, TimeUnits.DAY).humanizeDiff()} //через 7 дней
            ${Date().add(-400, TimeUnits.DAY).humanizeDiff()} //более года назад
            ${Date().add(400, TimeUnits.DAY).humanizeDiff()} //более чем через год
        """.trimIndent()
        )
    }

    @Test
    fun test_transliteration() {
        println(
            """
            ${Utils.transliteration("Женя Стереотипов")} //Zhenya Stereotipov
            ${Utils.transliteration("Amazing Петр", "_")} //Amazing_Petr
        """.trimIndent()
        )
    }

    @Test
    fun test_initials() {
        println(
            """
            ${Utils.toInitials("john", "doe")} //JD
            ${Utils.toInitials("John", null)} //J
            ${Utils.toInitials(null, null)} //null
            ${Utils.toInitials(" ", "")} //null
        """.trimIndent()
        )
    }

    @Test
    fun test_parse_fullname() {
        println(
            """
            ${Utils.parseFullName(null)} //null null
            ${Utils.parseFullName("")} //null null
            ${Utils.parseFullName(" ")} //null null
            ${Utils.parseFullName("John")} //John null
        """.trimIndent()
        )
    }
}
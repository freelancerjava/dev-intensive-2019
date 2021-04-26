package uz.paynet.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanazeDiff(date: Date = Date()): String {
    val diff: Long = date.time - this.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    return when {
        days > 360 -> "более года назад"
        hours > 26 && days <= 360 -> "$days дней назад"
        hours in 22..26 -> "день назад"
//        hours <= 26 && hours > 22 -> "день назад"
        minutes >= 75 && hours <= 22 -> "$hours часов назад"
        minutes in 45..75 -> "час назад"
//        minutes < 75 && minutes >= 45 -> "час назад"
        seconds > 75 && minutes < 45 -> "$minutes минут назад"
        seconds in 45..75 -> "минуту назад"
//        seconds >= 45 && seconds <= 75 -> "минуту назад"
//        seconds > 1 -> "$seconds секунд назад"
        seconds > 1 || seconds < 45 -> "несколько секунд назад"
        else -> "Только что"
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
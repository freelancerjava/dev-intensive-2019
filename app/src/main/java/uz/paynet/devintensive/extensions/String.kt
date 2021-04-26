package uz.paynet.devintensive.extensions

import org.jsoup.Jsoup

fun String.truncate(len: Int): String {
    return this.subSequence(0, len) as String + "..."
}

fun String.stripHtml():String{
    return Jsoup.parse(this).text()
}

fun String.getFirst(): String {
    return this.subSequence(0, 1) as String
}
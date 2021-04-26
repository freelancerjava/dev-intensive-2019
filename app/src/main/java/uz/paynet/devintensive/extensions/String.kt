package uz.paynet.devintensive.extensions

import org.jsoup.Jsoup

fun String.truncate(len: Int = 16): String {
    val temp = this.trim()
    return if(temp.length > len) "${(subSequence(0, len) as String).trim()}..." else trim()
}

fun String.stripHtml():String{
    return Jsoup.parse(this).text()
}

fun String.getFirst(): String {
    if(this.trim().isEmpty()) return ""
    return (this.subSequence(0, 1) as String).toUpperCase()
}
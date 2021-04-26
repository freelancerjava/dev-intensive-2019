package uz.paynet.devintensive.utils

import android.R.id.message
import uz.paynet.devintensive.extensions.getFirst


object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return Pair(if(firstName?.trim() == "") null else firstName  , if(lastName?.trim() == "") null else lastName  )
    }

    fun transliteration(payload: String, divider: String = " "): String {

        val abcCyr = charArrayOf(
            ' ','а','б','в','г','д','ѓ','е', 'ж','з','ѕ','и','ј','к','л','љ','м','н','њ','о','п','р','с','т', 'ќ','у', 'ф','х','ц','ч','џ','ш', 'я','ъ','ь','ы','э','ю', 'Я','Ъ','Ь','Ы','Э','Ю','А','Б','В','Г','Д','Ѓ','Е', 'Ж','З','Ѕ','И','Ј','К','Л','Љ','М','Н','Њ','О','П','Р','С','Т', 'Ќ', 'У','Ф', 'Х','Ц','Ч','Џ','Ш','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','/','-'
        )
        val abcLat = arrayOf(
            divider,"a","b","v","g","d","]","e","zh","z","y","i","j","k","l","q","m","n","w","o","p","r","s","t","'","u","f","h", "c","ch", "sh","sh", "ya", "","","i","e","yu", "Ya", "","","I","E","Yu", "A","B","V","G","D","}","E","Zh","Z","Y","I","J","K","L","Q","M","N","W","O","P","R","S","T","KJ","U","F","H", "C","Ch", "Sh","Sh", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","/","-"
        )
        val builder = StringBuilder()
        for (i in 0 until payload.length) {
            for (x in abcCyr.indices) {
                if (payload.get(i) === abcCyr.get(x)) {
                    builder.append(abcLat[x])
                }
            }
        }
        return builder.toString()

        return "transliterated"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first = firstName?.getFirst()
        val last = lastName?.getFirst()

        return when{
            first == null && last == null -> null
            "${first}${last}"=="" -> null
            else -> "${first ?: ""}${last ?: ""}"
        }
    }
}
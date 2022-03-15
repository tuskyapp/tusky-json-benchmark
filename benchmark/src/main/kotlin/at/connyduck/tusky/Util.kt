package at.connyduck.tusky

import android.text.Spanned

fun Spanned.hash(): Int {
    var h = 0
    val len = length
    if (h == 0 && len > 0) {
        for (i in 0 until len) {
            h = 31 * h + get(i).code
        }
    }
    return h
}

fun Spanned.stringEquals(other: Spanned): Boolean {
    var n = length
    if (n == other.length) {
        var i = 0
        while (n-- != 0) {
            if (get(i) != other[i]) return false
            i++
        }
        return true
    }
    return false
}
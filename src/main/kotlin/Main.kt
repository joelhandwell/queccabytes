import java.math.BigDecimal

/**
ref: https://stackoverflow.com/questions/3758606/how-can-i-convert-byte-size-into-a-human-readable-format-in-java
 */
fun humanReadableByteCount(bytes: BigDecimal, si: Boolean = true): String? {
    val unit = if (si) 1000.0.toBigDecimal() else 1024.0.toBigDecimal()
    if (bytes < unit)
        return "$bytes B"
    var result = bytes
    val unitsToUse =
        (if (si) "k" else "K") + "MGTPEZYRQXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" // ronna quecca from https://www.science.org/content/article/you-know-kilo-mega-and-giga-metric-system-ready-ronna-and-quecca
    var i = 0
    val unitsCount = unitsToUse.length
    while (true) {
        //result /= unit
        var divided = result.divide(unit)
        result = divided
        if (result < unit || i == unitsCount - 1)
            break
        ++i
    }
    return with(StringBuilder(9)) {
        append(String.format("%.2f ", result))
        append(unitsToUse[i])
        if (si) append('B') else append("iB")
    }.toString()
}

fun main() {
    (1..1000).forEach {
        val power = it
        val number = BigDecimal.valueOf(2).pow(power)
        val withPrefix = humanReadableByteCount(number)
        println("2 ^ %3d = %9s = $number".format(power, withPrefix))
    }
}
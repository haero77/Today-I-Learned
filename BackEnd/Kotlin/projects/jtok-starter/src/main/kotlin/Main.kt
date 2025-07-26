package org.example

fun main() {
    var str: String? = null
    println(str?.length ?: 0)

    println(startsWithA4(null))
}

fun startsWithA1(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("str is null")
}

fun startsWithA2(str: String?): Boolean? {
    return str?.startsWith("A") // 앞에 변수가 null이면 null(Boolean?) 반환
}

fun startsWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?:  false // 앞에 변수가 null이면 false(Boolean) 반환
}

fun startsWithA4(str: String?): Boolean {
     return str!!.startsWith("A")
}

fun calculate(number: Long?): Long {
    number ?: return 0 // number가 null이면 0 반환

    // 다음 로직
    return 0
}
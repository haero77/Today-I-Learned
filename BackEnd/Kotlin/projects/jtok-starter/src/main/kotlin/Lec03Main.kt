fun main() {
    val name = "haero77"

    val format = """
    abc
    efg
    ${name}
    """.trimIndent()

    println(format)

    val str = "ABCD"
    println(str[0])
}

fun printIfPerson(obj: Any?) {
    val person = obj as? Person // person null 가능
    println(person?.name)
}

fun printIfNotPerson(obj: Any) {
    if (obj !is Person) {
        println("Not a person")
    }
}


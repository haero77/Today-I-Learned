package org.example

import Person

fun main() {
    val person = Person("Allice")
    startWithA(person.name)
}

fun startWithA(str: String): Boolean {
    return str.startsWith("A")
}
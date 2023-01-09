package nl.erwinolie.`Advent-of-Code-2015`.`05 - Doesn't He Have Intern-Elves For This`

import nl.erwinolie.extensions.input

val input = input().lines()

fun main() {
    val answer1 = input
        .filter { it.count { it in "aeiou" } >= 3 }
        .filter { it.zip("." + it).any { it.first == it.second } }
        .filter { s -> listOf("ab", "cd", "pq", "xy").all { it !in s } }
        .count()
    println(answer1)

    val answer2 = input
        .filter { "^.*(..).*\\1.*$".toRegex().matches(it) }
        .filter { "^.*(.).\\1.*$".toRegex().matches(it) }
        .count()
    println(answer2)
}

package nl.erwinolie.`Advent-of-Code-2015`.`04 - The Ideal Stocking Stuffer`

import nl.erwinolie.extensions.input
import nl.erwinolie.extensions.md5

val input = input()

val hashesGenerator = sequence {
    var i = 0
    while (true) {
        yield(md5(input + i))
        i++
    }
}

fun main() {
    val answer1 = hashesGenerator
        .indexOfFirst { it.startsWith("00000") }
    println(answer1)

    val answer2 = hashesGenerator
        .indexOfFirst { it.startsWith("000000") }
    println(answer2)
}

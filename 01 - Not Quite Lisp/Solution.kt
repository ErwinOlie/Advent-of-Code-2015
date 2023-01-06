package nl.erwinolie.`Advent of Code 2015`.`01 - Not Quite Lisp`

import nl.erwinolie.extensions.input

fun main() {
    val answer1 = input()
        .map { if (it == '(') 1 else -1 }
        .sum()
    println(answer1)

    val answer2 = input()
        .map { if (it == '(') 1 else -1 }
        .runningReduce { acc, i -> acc + i }
        .indexOfFirst { it == -1 } + 1
    println(answer2)
}

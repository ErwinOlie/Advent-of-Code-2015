package nl.erwinolie.`Advent-of-Code-2015`.`02 - I Was Told There Would Be No Math`

import nl.erwinolie.extensions.input
import nl.erwinolie.extensions.toRectangle

val boxes = input()
    .lines()
    .map { "^(\\d+)x(\\d+)x(\\d+)$".toRegex().find(it)!! }
    .map { it.toRectangle() }

fun main() {
    val answer1 = boxes
        .sumOf { it.area() + it.smallestSideArea() }
    println(answer1)

    val answer2 = boxes
        .sumOf { it.shortestDistanceAround() + it.volume() }
    println(answer2)
}

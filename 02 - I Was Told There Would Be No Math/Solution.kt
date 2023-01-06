package nl.erwinolie.`Advent of Code 2015`.`02 - I Was Told There Would Be No Math`

import nl.erwinolie.extensions.input

val boxes = input()
    .lines()
    .map {
        val (l, w, h) = "^(\\d+)x(\\d+)x(\\d+)$".toRegex().find(it)!!.destructured
        Box(l.toInt(), w.toInt(), h.toInt())
    }

data class Box(
    val l: Int,
    val w: Int,
    val h: Int
) {
    fun surfaceArea() =
        listOf(2*l*w, 2*w*h, 2*h*l).sum()

    fun surfaceAreaSmallestSide() =
        listOf(l*w, w*h, h*l).min()

    fun shortestDistanceAround() =
        listOf(2*l, 2*w, 2*h)
            .sorted().take(2)
            .sum()

    fun cubicVolume() =
        l * w * h
}

fun main() {
    val answer1 = boxes
        .sumOf { it.surfaceArea() + it.surfaceAreaSmallestSide() }
    println(answer1)

    val answer2 = boxes
        .sumOf { it.shortestDistanceAround() + it.cubicVolume() }
    println(answer2)
}

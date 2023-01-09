package nl.erwinolie.`Advent-of-Code-2015`.`06 - Probably a Fire Hazard`

import nl.erwinolie.extensions.Point2D
import nl.erwinolie.extensions.input

val instructions = input()
    .lines()
    .map { "^(.+) (\\d+),(\\d+) through (\\d+),(\\d+)".toRegex().find(it)!! }
    .map {
        val (action, lcx, lcy, rcx, rcy) = it.destructured
        Instruction(action, Point2D(lcx, lcy), Point2D(rcx, rcy))
    }

data class Instruction(
    val action: String,
    val lCorner: Point2D,
    val rCorner: Point2D
)

fun litLightView1(x: Int, y: Int) =
    litLightView1(x, y, instructions.size - 1)

fun litLightView1(x: Int, y: Int, layer: Int): Boolean {
    if (layer < 0) {
        return false
    }
    val instruction = instructions[layer]
    if (instruction.lCorner.x > x || instruction.rCorner.x < x) {
        return litLightView1(x, y, layer - 1)
    }
    if (instruction.lCorner.y > y || instruction.rCorner.y < y) {
        return litLightView1(x, y, layer - 1)
    }
    if (instruction.action == "turn on") {
        return true
    }
    if (instruction.action == "turn off") {
        return false
    }
    if (instruction.action == "toggle") {
        return litLightView1(x, y, layer - 1).not()
    }
    throw IllegalStateException()
}

fun litLightView2(x: Int, y: Int) =
    instructions
        .filter {
            it.lCorner.x <= x && it.rCorner.x >= x &&
                    it.lCorner.y <= y && it.rCorner.y >= y
        }
        .map {
            when (it.action) {
                "turn on" -> 1
                "turn off" -> -1
                "toggle" -> 2
                else -> throw IllegalStateException()
            }
        }
        .fold(0) { acc, i ->
            if (acc + i > 0) {
                acc + i
            } else {
                0
            }
        }

fun main() {
    val answer1 = (0..999).flatMap { x ->
        (0..999).filter { y ->
            litLightView1(x, y)
        }
    }.count()
    println(answer1)

    val answer2 = (0..999).flatMap { x ->
        (0..999).map { y ->
            litLightView2(x, y)
        }
    }.sum()
    println(answer2)
}

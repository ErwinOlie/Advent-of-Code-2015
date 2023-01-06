package nl.erwinolie.`Advent-of-Code-2015`.`03 - Perfectly Spherical Houses in a Vacuum`

import nl.erwinolie.extensions.Point2D
import nl.erwinolie.extensions.input

class Radio {
    private val instructions = input()
    private var instructionPointer = 0

    fun hasInstruction() =
        instructionPointer < instructions.length

    fun nextInstruction(): Instruction {
        val direction = instructions[instructionPointer++]
        return Instruction.ofDirection(direction)
    }
}

enum class Instruction(
    private val direction: Char,
    val moveDelta: Point2D
) {
    NORTH('^', Point2D(0, -1)),
    SOUTH('v', Point2D(0, 1)),
    EAST('>', Point2D(1, 0)),
    WEST('<', Point2D(-1, 0));

    companion object {
        fun ofDirection(direction: Char) =
            values().first { it.direction == direction }
    }
}

class Santa(
    private val radio: Radio,
    private val visitedHouses: MutableSet<Point2D> = mutableSetOf()
) {
    private var position = Point2D(0, 0)

    fun deliver() {
        while (radio.hasInstruction()) {
            move(radio.nextInstruction())
        }
    }

    fun move(instruction: Instruction) {
        position += instruction.moveDelta
        visitedHouses += position
    }

    fun visitedHousesCount() =
        visitedHouses.size
}

fun main() {
    println(answer1()) // 2592
    println(answer2()) // 2360
}

fun answer1(): Int {
    val santa = Santa(Radio(), mutableSetOf(Point2D(0, 0)))
    santa.deliver()
    return santa.visitedHousesCount()
}

fun answer2(): Int {
    val radio = Radio()
    val visitedHouses = mutableSetOf(Point2D(0, 0))

    val santas = listOf(
        Santa(radio, visitedHouses),
        Santa(radio, visitedHouses)
    )
    var santaPointer = 0

    while (radio.hasInstruction()) {
        val santa = santas[santaPointer]
        santaPointer = (santaPointer + 1) % santas.size

        val instruction = radio.nextInstruction()
        santa.move(instruction)
    }

    return visitedHouses.size
}

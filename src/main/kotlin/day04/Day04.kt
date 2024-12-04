package day04

import println
import readInput

data class Pos(val x: Int, val y: Int, val char: Char)


fun checkXMAS(pos: Pos, puzzle: List<List<Pos>>): Boolean {
    return try {
        val d1 = listOf(
            puzzle[pos.y - 1][pos.x + 1].char,
            puzzle[pos.y][pos.x].char,
            puzzle[pos.y + 1][pos.x - 1].char
        ).joinToString("")
        val d2 = listOf(
            puzzle[pos.y - 1][pos.x - 1].char,
            puzzle[pos.y][pos.x].char,
            puzzle[pos.y + 1][pos.x + 1].char
        ).joinToString("")
        pos.char == 'A' && (d1 == "MAS" || d1 == "SAM") && (d2 == "MAS" || d2 == "SAM")
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}

fun allWords(input: List<String>): List<String> {

    val puzzle = input.asSequence().mapIndexed { y, chars ->
        chars.mapIndexed { x, c ->
            Pos(x, y, c)
        }
    }.flatten().toList()

    return (puzzle.groupBy { it.x }.entries.map {
        it.value.map { it.char }.joinToString("")
    } + puzzle.groupBy { it.y }.entries.map {
        it.value.map { it.char }.joinToString("")
    } + puzzle.groupBy { it.y + it.x }.entries.map {
        it.value.map { it.char }.joinToString("")
    } + puzzle.groupBy { it.x - it.y }.entries.map {
        it.value.map { it.char }.joinToString("")
    })

}

fun main() {

    fun part1(input: List<String>): Int = allWords(input).sumOf {
        (Regex(Regex.escape("SAMX"))
            .findAll(it)
            .count()) +
                (Regex(Regex.escape("XMAS"))
                    .findAll(it)
                    .count())

    }

    fun part2(input: List<String>) = input.mapIndexed { y, chars ->
        chars.mapIndexed { x, c ->
            Pos(x, y, c)
        }
    }.let { puzzle ->
        puzzle.flatten().toList()
            .filter { pos ->
                checkXMAS(pos, puzzle)
            }.distinct().size
    }



    part1(readInput("day04/input")).println()
    part2(readInput("day04/input")).println()
}

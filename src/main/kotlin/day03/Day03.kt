package day03

import println
import readInput


fun main() {

    fun part1(input: List<String>) = input.sumOf {
        "mul\\((\\d+),(\\d+)\\)".toRegex().findAll(it)
            .map { it.groupValues }.toList().fold(0L) { acc, (_, a, b) ->
                acc + a.toInt() * b.toInt()
            }
    }

    fun part2(input: List<String>) =
        "mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))".toRegex().findAll(input.joinToString(""))
            .map { it.groupValues }.toList().fold(0 to true) { (acc, flag), (_, a, b, enable, disable) ->
                when {
                    enable.isNotEmpty() -> acc to true
                    disable.isNotEmpty() -> acc to false
                    else -> acc + ((a.toInt() * b.toInt()).takeIf { flag } ?: 0) to flag
                }
            }.first


    part1(readInput("day03/input")).println()
    part2(readInput("day03/input")).println()
}

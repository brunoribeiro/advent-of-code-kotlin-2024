package day02

import println
import readInput
import kotlin.math.abs


fun main() {

    fun validateReport(report: List<Pair<Int, Int>>): Boolean =
        (report.all { (a, b) -> a <= b } || report.all { (a, b) -> a >= b }) && report.all { (a, b) -> (abs(a - b) in 1..3) }


    fun part1(input: List<String>) = input
        .map { it.split("\\s+".toRegex()).map { it.toInt() } }
        .filter { validateReport(it.zipWithNext()) }.size


    fun part2(input: List<String>) = input
        .map { it.split("\\s+".toRegex()).map { it.toInt() } }
        .filterNot { validateReport(it.zipWithNext()) }
        .filter { report ->
            (report.indices.map { index ->
                report.filterIndexed { i, _ -> i != index }
            }).any { validateReport(it.zipWithNext()) }
        }
        .size


    part1(readInput("day02/input")).println()
    (part1(readInput("day02/input")) + part2(readInput("day02/input"))).println()
}

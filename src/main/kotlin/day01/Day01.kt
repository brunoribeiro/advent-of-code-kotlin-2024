package day01

import println
import readInput


fun main() {

    fun part1(input: List<String>): Int = input
        .flatMap { it.split("\\s+".toRegex()).mapIndexed { i, s -> (i to s) } }
        .groupBy { it.first }
        .map { it.value.map { it.second }.sorted() }
        .let { (first, second) ->
            first.mapIndexed { index, s -> Math.abs(s.toInt() - second.get(index).toInt()) }
                .sum()
        }


    fun part2(input: List<String>): Int = input
        .flatMap { it.split("\\s+".toRegex()).mapIndexed { i, s -> (i to s) } }
        .groupBy { it.first }
        .map { it.value.map { it.second }.sorted() }
        .let { (first, second) ->
            first.sumOf { f -> f.toInt() * second.count { it == f } }
        }


    part1(readInput("day01/input")).println()
    part2(readInput("day01/input")).println()
}

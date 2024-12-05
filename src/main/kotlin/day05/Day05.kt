package day05

import println
import readInput

fun main() {

    fun sortUpdate(update: List<Int>, dependencies: MutableMap<Int, List<Int>>): List<Int> {
        return when {
            update.size == 1 -> update
            else -> {
                val parents = dependencies[update[0]] ?: emptyList()
                val match = parents.firstOrNull { update.contains(it) }
                match?.let {
                    sortUpdate(listOf(match) + (update - match), dependencies)
                } ?: (listOf(update[0]) + sortUpdate(update.subList(1, update.size), dependencies))

            }
        }

    }

    fun isCorrect(update: List<Int>, dependencies: MutableMap<Int, List<Int>>): Boolean {
        return when {
            update.size == 1 -> true
            else -> {
                dependencies[update.first()].orEmpty().none { update.contains(it) } && isCorrect(
                    update.subList(
                        1,
                        update.size
                    ), dependencies
                )
            }
        }
    }

    fun part1(input: List<String>): Int {

        val rules: List<Pair<Int, Int>> =
            input.takeWhile { it.isNotBlank() }.map { it.split("|") }.map { (a, b) -> a.toInt() to b.toInt() }
        val updates = input.drop(rules.size + 1).map { it.split(",").map { it.toInt() } }
        val dependencies = rules
            .fold(mutableMapOf<Int, List<Int>>()) { acc, (a, b) ->
                acc.apply {
                    this[b] = (this[b] ?: emptyList()) + a
                }
            }
        return updates.filter { isCorrect(it, dependencies) }.sumOf { it[it.size / 2] }

    }


    fun part2(input: List<String>): Int {

        val rules: List<Pair<Int, Int>> =
            input.takeWhile { it.isNotBlank() }.map { it.split("|") }.map { (a, b) -> a.toInt() to b.toInt() }
        val updates = input.drop(rules.size + 1).map { it.split(",").map { it.toInt() } }
        val dependencies = rules
            .fold(mutableMapOf<Int, List<Int>>()) { acc, (a, b) ->
                acc.apply {
                    this[b] = (this[b] ?: emptyList()) + a
                }
            }
        return updates.filter { !isCorrect(it,dependencies) }.map { sortUpdate(it,dependencies) }.sumOf { it[it.size / 2] }
    }


    part1(readInput("day05/test")).println()
    part2(readInput("day05/input")).println()
}

package com.github.rileymichael

import com.github.rileymichael.day1.Day1
import kotlin.time.measureTimedValue

fun main() {
    // todo: reflection to get rid of this.. arg to run spcific day..
    val days = listOf(
        Day1
    )
    days.lastOrNull()?.run { solve() }
}

private fun <T, R> Puzzle<T, R>.solve() {
    println("Solving day $day")
    solutions.forEachIndexed { index, solution ->
        val input = this::class.java.classLoader.getResourceAsStream("Day$day.txt")
        requireNotNull(input) { "Input for day $day not found" }
        input.bufferedReader().useLines {
            val parse = measureTimedValue { parse(it) }
            val answer = measureTimedValue { solve(solution, parse.value) }
            println("""
                - Part ${index + 1} -
                  parsed in: ${parse.duration}
                  solved in: ${answer.duration}
                  solution: ${answer.value}
            """.trimIndent()
            )
        }
    }
}
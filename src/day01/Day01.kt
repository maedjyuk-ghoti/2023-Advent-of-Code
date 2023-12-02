package day01

import println
import readInput

val digitToInt = mapOf(
    "zero" to 0,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)
val digitRegex = "(zero|one|two|three|four|five|six|seven|eight|nine)".toRegex()
fun String.containsDigitOrInt(): Boolean =
    contains("\\d".toRegex()) || contains(digitRegex)

fun part1(input: List<String>): Int =
    input.sumOf { artsyCoord ->
        artsyCoord.first(Char::isDigit).digitToInt() * 10 +
                artsyCoord.last(Char::isDigit).digitToInt()
    }

fun String.replaceDigitWithInt(): String =
    replace(digitRegex) { matchResult -> digitToInt.getValue(matchResult.value).toString() }

fun part2(input: List<String>): Int =
    input.sumOf { artsyCoord ->
        val first = artsyCoord.windowed(5, partialWindows = true)
            .first(String::containsDigitOrInt)
            .let(String::replaceDigitWithInt)
            .first(Char::isDigit)
            .digitToInt()

        val last = artsyCoord.windowed(5, partialWindows = true)
            .last(String::containsDigitOrInt)
            .let(String::replaceDigitWithInt)
            .last(Char::isDigit)
            .digitToInt()

        first * 10 + last
    }

fun main() {
    val testInput1 = readInput("day01/test_input_1")
    check(part1(testInput1) == 142)

    val testInput2 = readInput("day01/test_input_2")
    check(part2(testInput2) == 281)

    val input = readInput("day01/input")
    part1(input).println()
    part2(input).println()
}

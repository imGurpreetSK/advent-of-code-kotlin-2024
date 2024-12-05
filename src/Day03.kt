import java.io.BufferedReader
import kotlin.io.path.Path
import kotlin.io.path.reader

class Day03 {
    fun main() {

        fun part1(reader: BufferedReader): Int = reader.useLines {
            it.sumOf { findValidOccurrences(it) }
        }

        fun part2(input: String): Int {
            val firstWindow = firstWindow(input)
            return findValidOccurrences(firstWindow) +
                    otherWindows(input.replace(firstWindow, "")).sum()
        }

        part1(Path("src/sample/Day03_part2.txt").reader().buffered(2048)).println()
        part2(readInput("input/Day03").joinToString(separator = "")).println()
    }

    private fun firstWindow(input: String): String {
        val windowEnd = input.indexOf("don't()", ignoreCase = true)
        return input.substring(0..<windowEnd)
    }

    private fun otherWindows(input: String): List<Int> {
        val input = StringBuilder(input) // Explicit shadowing.
        val sumList = mutableListOf<Int>()

        while (input.isNotEmpty()) {
            var start = input.indexOf("do()")
            if (start == -1) break // No starting marker found.

            var end = input.safeIndexOf("don't()")
            if (end < start) { // don't() before do()
                input.replace(0, start, "")
                start = 0
                end = input.safeIndexOf("don't()")
            }

            sumList.add(findValidOccurrences(input.substring(start, end)))
            input.replace(0, end, "")
        }

        return sumList
    }

    private fun java.lang.StringBuilder.safeIndexOf(s: String): Int {
        val potentialMatch = this.indexOf(s)
        return if (potentialMatch == -1) {
            this.lastIndex
        } else {
            potentialMatch
        }
    }

    private fun findValidOccurrences(input: String): Int {
        return Regex("""mul\((\d+),(\d+)\)""").findAll(input)
            .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
            .sum()
    }
}

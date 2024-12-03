import java.io.BufferedReader
import kotlin.io.path.*

fun main() {

    fun part1(inputFile: BufferedReader): Int = inputFile.useLines {
        it.sumOf { findValidOccurrences(it) }
    }

    fun part2(input: BufferedReader): Int = TODO()

    val input = Path("src/input/Day03.txt").reader().buffered(2048)
    part1(input).println()
    part2(input).println()
}

private fun findValidOccurrences(input: String): Int {
    return Regex("""mul\((\d+),(\d+)\)""").findAll(input)
        .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
        .sum()
}

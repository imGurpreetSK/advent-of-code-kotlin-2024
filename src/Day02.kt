fun main() {

    fun part1(input: List<String>): Int = input.count { isSafe(it.toIntList()) }

    fun part2(input: List<String>): Int = input.count { isLenientSafe(it.toIntList()) }

    val input = readInput("input/Day02")
    part1(input).println()
    part2(input).println()
}

private fun isSafe(input: List<Int>): Boolean {
    return input.windowed(2).all { (a, b) -> a > b && a - b in 1..3 } ||
            input.windowed(2).all { (a, b) -> a < b && b - a in 1..3 }
}

private fun isLenientSafe(input: List<Int>): Boolean = isSafe(input) || input.indices.any { isSafe(input.omit(it)) }

private fun String.toIntList(): List<Int> = split(Regex("\\s+")).map { it.toInt() }

private fun List<Int>.omit(index: Int): List<Int> = filterIndexed { idx, _ -> idx != index }

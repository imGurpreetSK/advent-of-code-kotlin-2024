fun main() {
    fun part1(input: List<String>): Int = input.count { isSafe(it.toIntList()) }

    val input = readInput("input/Day02")
    part1(input).println()
}

private fun isSafe(input: List<Int>): Boolean {
    return input.windowed(2).all { (a, b) -> a > b && a - b in 1..3 } ||
            input.windowed(2).all { (a, b) -> a < b && b - a in 1..3 }
}

private fun String.toIntList(): List<Int> = split(Regex("\\s+")).map { it.toInt() }

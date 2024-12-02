import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val first = mutableListOf<Int>()
        val second = mutableListOf<Int>()

        input.asSequence()
            .map { it.split(Regex("\\s+")) }
            .filter { it.isNotEmpty() }
            .map { it[0].toInt() to it[1].toInt() }
            .onEach {
                first.add(it.first)
                second.add(it.second)
            }
            .count()

        first.sort()
        second.sort()

        return first.zip(second)
            .fold(0) { acc, pair -> acc + abs(pair.first - pair.second) }
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input/Day01")
    part1(input).println()
}

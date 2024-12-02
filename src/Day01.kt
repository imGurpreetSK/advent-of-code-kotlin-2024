import kotlin.math.abs

class Day01 {
    fun solution() {
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

        fun part2(input: List<String>): Int {
            val first = mutableListOf<Int>()
            val map = hashMapOf<Int, Int>()

            input.asSequence()
                .map { it.split(Regex("\\s+")) }
                .filter { it.isNotEmpty() }
                .map { it[0].toInt() to it[1].toInt() }
                .onEach { first.add(it.first) }
                .onEach { map[it.second] = (map[it.second] ?: 0) + 1 }
                .count()

            return first.fold(0) { acc, num -> acc + num * (map[num] ?: 0) }
        }

        // Read the input from the `src/Day01.txt` file.
        val input = readInput("input/Day01")
        part1(input).println()
        part2(input).println()
    }
}

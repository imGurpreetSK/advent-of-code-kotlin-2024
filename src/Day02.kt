fun main() {

    fun part1(input: List<String>): Int {
        return input.count {
            isSafe(it.split(Regex("\\s+")).map { it.toInt() })
        }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // Read the input from the `src/Day01.txt` file.
//    val input = readInput("sample/Day02_part1")
    val input = readInput("input/Day02")
    part1(input).println()
}

private fun isSafe(list: List<Int>): Boolean {
    var last = list.first()

    // 1 = increasing, -1 = decreasing, 0 = unknown.
    val pattern = if (last < list[1]) {
        1
    } else if (last > list[1]) {
        -1
    } else {
        return false
    }

    list.subList(1, list.size).forEach {
        last = if (pattern == 1) {
            if (it - last in 1..3) {
                it
            } else {
                return false
            }
        } else {
            if (last - it in 1..3) {
                it
            } else {
                return false
            }
        }
    }

    return true
}

fun main() {

    fun part1(input: List<String>): Int {
        var count = 0
        val maxX = input.firstOrNull()?.lastIndex ?: -1
        val maxY = input.lastIndex

        for (y in 0..maxY) {
            for (x in 0..maxX) {
                if (input[x][y] != 'X') continue
                count += findOccurrencesCountAround(input, x to y, 0 to maxX, 0 to maxY)
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        return 0 // TODO(gs)
    }

    val input = readInput("sample/Day04_part2")
    part1(input).println()
    part2(input).println()
}

private fun findOccurrencesCountAround(
    input: List<String>,
    coordinates: Pair<Int, Int>,
    xLimits: Pair<Int, Int>,
    yLimits: Pair<Int, Int>,
): Int {
    val MAS = "MAS"
    var count = 0
    Move.entries.forEach { move ->
        when (move) {
            Move.UP -> {
                if (coordinates.second - 3 >= yLimits.first) {
                    var found = ""
                    repeat(3) { index ->
                        found += input[coordinates.first][coordinates.second + (move.yTravel * (index + 1))]
                    }
                    if (found == MAS) count++
                }
            }

            Move.DOWN -> {
                if (coordinates.second + 3 <= yLimits.second) {
                    var found = ""
                    repeat(3) { index ->
                        found += input[coordinates.first][coordinates.second + (move.yTravel * (index + 1))]
                    }
                    if (found == MAS) count++
                }
            }

            Move.LEFT -> {
                if (coordinates.first - 3 >= xLimits.first) {
                    var found = ""
                    repeat(3) { index ->
                        found += input[coordinates.first + (move.xTravel * (index + 1))][coordinates.second]
                    }
                    if (found == MAS) count++
                }
            }

            Move.RIGHT -> {
                if (coordinates.first + 3 <= xLimits.second) {
                    var found = ""
                    repeat(3) { index ->
                        found += input[coordinates.first + (move.xTravel * (index + 1))][coordinates.second]
                    }
                    if (found == MAS) count++
                }
            }

            Move.UP_LEFT -> {
                if (coordinates.second - 3 >= yLimits.first && coordinates.first - 3 >= xLimits.first) {
                    var found = ""
                    repeat(3) { iteration ->
                        found += input[coordinates.first + (move.xTravel * (iteration + 1))][coordinates.second + (move.yTravel * (iteration + 1))]
                    }
                    if (found == MAS) count++
                }
            }

            Move.UP_RIGHT -> {
                if (coordinates.second - 3 >= yLimits.first && coordinates.first + 3 <= xLimits.second) {
                    var found = ""
                    repeat(3) { iteration ->
                        found += input[coordinates.first + (move.xTravel * (iteration + 1))][coordinates.second + (move.yTravel * (iteration + 1))]
                    }
                    if (found == MAS) count++
                }
            }

            Move.DOWN_LEFT -> {
                if (coordinates.second + 3 <= yLimits.second && coordinates.first - 3 >= xLimits.first) {
                    var found = ""
                    repeat(3) { iteration ->
                        found += input[coordinates.first + (move.xTravel * (iteration + 1))][coordinates.second + (move.yTravel * (iteration + 1))]
                    }
                    if (found == MAS) count++
                }
            }

            Move.DOWN_RIGHT -> {
                if (coordinates.second + 3 <= yLimits.second && coordinates.first + 3 <= xLimits.second) {
                    var found = ""
                    repeat(3) { iteration ->
                        found += input[coordinates.first + (move.xTravel * (iteration + 1))][coordinates.second + (move.yTravel * (iteration + 1))]
                    }
                    if (found == MAS) count++
                }
            }
        }
    }

    return count
}

private enum class Move(val xTravel: Int, val yTravel: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, -1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1)
}

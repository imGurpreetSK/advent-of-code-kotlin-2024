class Day04 {
    fun main() {
        fun part1(input: List<String>): Int {
            var count = 0
            val maxX = input.firstOrNull()?.lastIndex ?: -1
            val maxY = input.lastIndex

            for (y in 0..maxY) {
                for (x in 0..maxX) {
                    if (input[x][y] != 'A') continue
                    count += findOccurrencesCountAround(input, x to y, 0 to maxX, 0 to maxY)
                }
            }

            return count
        }

        fun part2(input: List<String>): Int {
            var count = 0
            val maxX = input.firstOrNull()?.lastIndex ?: -1
            val maxY = input.lastIndex

            for (y in 0..maxY) {
                for (x in 0..maxX) {
                    if (input[x][y] != 'A') continue
                    count += findOccurrencesCountAround(input, x to y, 0 to maxX, 0 to maxY)
                }
            }

            return count
        }

        val input = readInput("input/Day04")
//    part1(input).println()
        part2(input).println()
    }

    private fun findOccurrencesCountAround(
        input: List<String>,
        coordinates: Pair<Int, Int>,
        xLimits: Pair<Int, Int>,
        yLimits: Pair<Int, Int>,
    ): Int {
        val map = HashMap<Move, Char>(4)
        Move.entries.forEach { move ->
            when (move) {
                Move.UP_LEFT -> {
                    if (coordinates.second - 1 >= yLimits.first && coordinates.first - 1 >= xLimits.first) {
                        map[move] = input[coordinates.first + move.xTravel][coordinates.second + move.yTravel]
                    }
                }

                Move.UP_RIGHT -> {
                    if (coordinates.second - 1 >= yLimits.first && coordinates.first + 1 <= xLimits.second) {
                        map[move] = input[coordinates.first + move.xTravel][coordinates.second + move.yTravel]
                    }
                }

                Move.DOWN_LEFT -> {
                    if (coordinates.second + 1 <= yLimits.second && coordinates.first - 1 >= xLimits.first) {
                        map[move] = input[coordinates.first + move.xTravel][coordinates.second + move.yTravel]
                    }
                }

                Move.DOWN_RIGHT -> {
                    if (coordinates.second + 1 <= yLimits.second && coordinates.first + 1 <= xLimits.second) {
                        map[move] = input[coordinates.first + move.xTravel][coordinates.second + move.yTravel]
                    }
                }
            }
        }

        return if (map.values.count() == 4 &&
            map.values.all { it == 'M' || it == 'S' } &&
            !((map[Move.UP_LEFT] == 'M' && map[Move.DOWN_RIGHT] == 'M') ||
                    (map[Move.UP_RIGHT] == 'M' && map[Move.DOWN_LEFT] == 'M') ||
                    (map[Move.UP_LEFT] == 'S' && map[Move.DOWN_RIGHT] == 'S') ||
                    (map[Move.UP_RIGHT] == 'S' && map[Move.DOWN_LEFT] == 'S'))) {
            1
        } else {
            0
        }
    }

    private enum class Move(val xTravel: Int, val yTravel: Int) {
        UP_LEFT(-1, -1),
        UP_RIGHT(1, -1),
        DOWN_LEFT(-1, 1),
        DOWN_RIGHT(1, 1)
    }
}

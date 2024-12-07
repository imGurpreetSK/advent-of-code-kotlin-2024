fun main() {

    fun part1(input: List<String>): Int {
        var currentDirection: Direction? = null
        var currentPosition: Pair<Int, Int> = -1 to -1
        for (y in input.indices) {
            for (x in input[y].indices) {
                Direction.find(input[y][x])?.let {
                    currentPosition = x to y
                    currentDirection = it
                }
            }
        }

        val visited = mutableSetOf(currentPosition)
        while (true) {
            when (val next = findNext(input, currentPosition, currentDirection!!)) {
                NextElement.Obstruction -> currentDirection = currentDirection!!.next()

                is NextElement.Empty -> {
                    if (!visited.contains(next.x to next.y)) {
                        visited.add(next.x to next.y)
                    }
                    currentPosition = currentPosition.advance(currentDirection!!)
                }

                NextElement.Exit -> break
            }
        }

        return visited.count()
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    val input = readInput("input/Day06")
    part1(input).println()
//    part2(input).println()
}

private fun findNext(
    input: List<String>,
    currentPosition: Pair<Int, Int>,
    currentDirection: Direction
): NextElement = try {
    val y = currentPosition.second + currentDirection.y
    val x = currentPosition.first + currentDirection.x
    val item = input[y][x]

    if (item == '#') {
        NextElement.Obstruction
    } else {
        NextElement.Empty(x, y)
    }
} catch (e: Exception) {
    NextElement.Exit
}

private fun Pair<Int, Int>.advance(currentDirection: Direction): Pair<Int, Int> =
    first + currentDirection.x to second + currentDirection.y

private sealed interface NextElement {
    data object Obstruction : NextElement
    data class Empty(val x: Int, val y: Int) : NextElement
    data object Exit : NextElement
}

private enum class Direction(val x: Int, val y: Int) {

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0), ;

    fun next(): Direction = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }

    companion object {
        fun find(c: Char): Direction? = when (c) {
            '^' -> UP
            'v' -> DOWN
            '<' -> LEFT
            '>' -> RIGHT

            else -> null
        }
    }
}

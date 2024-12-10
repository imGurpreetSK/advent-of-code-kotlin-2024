import java.math.BigInteger

fun main() {

    fun part1(input: List<String>): BigInteger {
        val diskMap = mutableListOf<Item>()
        var id = 0
        input[0].forEachIndexed { index, c ->
            if (index % 2 == 0) {
                repeat(c.digitToInt()) {
                    diskMap.add(Item.File(id))
                }
                id++
            } else {
                repeat(c.digitToInt()) {
                    diskMap.add(Item.FreeSpace)
                }
            }
        }

        while (true) {
            val freeSpace = diskMap.indexOfFirst { it is Item.FreeSpace }
            val file = diskMap.indexOfLast { it is Item.File }
            if (freeSpace >= file) break
            diskMap.swap(freeSpace, file)
        }

        return diskMap
            .subList(0, diskMap.indexOfFirst { it is Item.FreeSpace })
            .withIndex()
            .sumOf { (index, item) -> ((item as Item.File).id * index).toBigInteger() }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    val input = readInput("input/Day09")
    part1(input).println()
//    part2(input).println()
}

private fun <E> MutableList<E>.swap(freeSpace: Int, file: Int) {
    val temp = this[file]
    this[file] = this[freeSpace]
    this[freeSpace] = temp
}

private sealed interface Item {

    data class File(val id: Int) : Item

    data object FreeSpace : Item
}
/*

    12345 -> 0..111....22222 -> 022111222

*/

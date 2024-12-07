class Day05 {
    fun main() {
        fun part1(input: List<String>): Int {
            val map: Map<Int, OrderStore> = getRules(input)

            var sum = 0
            input.asSequence()
                .filter { it.contains(",") }
                .map { it.split(",").map { it.toInt() } }
                .map { list ->
                    var shouldAdd = true
                    for (i in list.indices) {
                        if (i == 0) continue

                        if (map[list[i]]?.isSatisfied(list.subList(0, i)) == false) {
                            shouldAdd = false
                            break
                        }
                    }

                    if (shouldAdd) {
                        sum += list[list.lastIndex / 2]
                    } else {
                        0
                    }
                }
                .count()

            return sum
        }

        fun part2(input: List<String>): Int {
            TODO()
        }

        val input = readInput("sample/Day05_part2")
//    part1(input).println()
        part2(input).println()
    }

    private fun getRules(input: List<String>): Map<Int, OrderStore> {
        val map = mutableMapOf<Int, OrderStore>()
        input.asSequence()
            .takeWhile { it.isNotBlank() }
            .map {
                val rule = it.split("|").map { it.toInt() }
                val key = rule[0]
                val value = rule[1]
                val toPut = if (map[key] != null) {
                    map[key]?.add(value)
                    map[key]!! // Guaranteed single thread access.
                } else {
                    OrderStore(value)
                }
                map.put(key, toPut)
            }
            .count()

        return map
    }

    private class OrderStore(
        initialValue: Int
    ) {

        private val restrictions = mutableSetOf(initialValue)

        fun add(value: Int) {
            restrictions.add(value)
        }

        fun isSatisfied(subList: List<Int>): Boolean {
            return subList.all { !restrictions.contains(it) }
        }
    }
}

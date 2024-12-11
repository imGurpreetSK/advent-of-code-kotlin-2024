import java.math.BigInteger

fun main() {
    val input = readInput("input/Day11")
    part1(input).println()
}

@Suppress("NAME_SHADOWING")
private fun part1(input: List<String>): Int {
    var input = input[0].split(" ").map { it.toBigInteger() }
    repeat(25) {
        input = buildList {
            input.forEach { addAll(applyRulesFor(it)) }
        }
    }

    return input.size
}

private fun applyRulesFor(num: BigInteger): List<BigInteger> =
    if (num == BigInteger.ZERO) {
        listOf(BigInteger.ONE)
    } else if (num.toString().length % 2 == 0) {
        val element = num.toString()
        listOf(
            element.substring(0..<element.length / 2).toBigInteger(),
            element.substring(element.length / 2).toBigInteger(),
        )
    } else {
        listOf(num * BigInteger.valueOf(2024))
    }

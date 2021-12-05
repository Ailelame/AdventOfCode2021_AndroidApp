package com.jetdev.adventofcode.solution

import kotlin.math.pow
import kotlin.math.roundToInt

object Challenge3Part1 {
    fun resolve(input: List<String>): String {
        val gamaRateOccurenceByPosition = mutableListOf<Int>()
        val gamaRateResult = mutableListOf<Int>()
        // populate gamaRateOccurenceByPosition - kinda ugly
        input.first().forEach {
            gamaRateOccurenceByPosition.add(0)
        }

        input.map {
            it.forEachIndexed { index, c ->
                if (c.digitToIntOrNull() == 1) {
                    gamaRateOccurenceByPosition[index] += 1
                }
            }
        }
        gamaRateOccurenceByPosition.forEachIndexed { index, i ->
            if (i > input.size / 2) {
                gamaRateResult.add(index, 1)
            } else {
                gamaRateResult.add(index, 0)
            }
        }
        val gamaRate = gamaRateResult.joinToString("").binaryToDecimal()
        val epsilonRate = gamaRateResult.reverseDigits().joinToString("").binaryToDecimal()
        return (gamaRate * epsilonRate).toString()
    }

    fun String.binaryToDecimal(): Int {
        var sum = 0.0
        this.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * 2.0.pow(k.toDouble())
        }
        return sum.roundToInt()
    }

    fun MutableList<Int>.reverseDigits(): List<Int> {
        return this.map {
            val newValue = if (it == 0) {
                1
            } else {
                0
            }
            newValue
        }
    }
}
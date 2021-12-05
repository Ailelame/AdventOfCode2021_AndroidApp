package com.jetdev.adventofcode.solution

import com.jetdev.adventofcode.solution.Challenge3Part1.binaryToDecimal

object Challenge3Part2 {
    fun resolve(input: List<String>): String {

        val oxygenGeneratorRating = findOxygenGeneratorRating(input, 0)
        val co2ScrubberRating = findCo2ScrubberRating(input, 0)

        return (oxygenGeneratorRating.binaryToDecimal() * co2ScrubberRating.binaryToDecimal()).toString()
    }


    private fun findOxygenGeneratorRating(list: List<String>, index: Int): String {
        if (list.size == 1)
            return list.first() // result

        var zeroCountForIndex = 0
        list.forEach {
            if (it[index].digitToInt() == 0)
                zeroCountForIndex += 1
        }

        return if (zeroCountForIndex <= list.size / 2) {
            findOxygenGeneratorRating(list.filter { it[index] == '1' }, index + 1)
        } else {
            findOxygenGeneratorRating(list.filter { it[index] == '0' }, index + 1)
        }
    }


    private fun findCo2ScrubberRating(list: List<String>, index: Int): String {
        if (list.size == 1)
            return list.first() // result

        var zeroCountForIndex = 0
        list.forEach {
            if (it[index].digitToInt() == 0)
                zeroCountForIndex += 1
        }

        return if (zeroCountForIndex > list.size / 2) {
            findCo2ScrubberRating(list.filter { it[index] == '1' }, index + 1)
        } else {
            findCo2ScrubberRating(list.filter { it[index] == '0' }, index + 1)
        }
    }

}
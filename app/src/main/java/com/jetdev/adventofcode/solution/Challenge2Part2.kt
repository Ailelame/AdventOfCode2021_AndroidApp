package com.jetdev.adventofcode.solution

import com.jetdev.adventofcode.solution.Challenge2Part1.extractNumber

object Challenge2Part2 {
    fun resolve(input: List<String>): String {
        var forward = 0
        var depth = 0
        var aim = 0

        input.forEach {
            if (it.contains("forward")) {
                forward += it.extractNumber()
                depth += aim * it.extractNumber()
            } else if (it.contains("down")) {
                aim += it.extractNumber()
            } else if (it.contains("up")) {
                aim -= it.extractNumber()
            }
        }
        return (forward * depth).toString()
    }
}
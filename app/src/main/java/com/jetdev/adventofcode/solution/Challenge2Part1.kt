package com.jetdev.adventofcode.solution

object Challenge2Part1 {
    fun resolve(input: List<String>): String {
        var forward = 0
        var depth = 0

        input.forEach {
            if (it.contains("forward")) {
                forward += it.extractNumber()
            } else if (it.contains("down")) {
                depth += it.extractNumber()
            } else if (it.contains("up")) {
                depth -= it.extractNumber()
            }
        }
        return (forward * depth).toString()
    }

    fun String.extractNumber(): Int = this.replace("[^0-9]".toRegex(), "").toInt()

}
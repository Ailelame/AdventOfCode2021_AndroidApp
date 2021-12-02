package com.jetdev.adventofcode.solution

object Challenge1Part1 {
    fun resolve(input: List<String>): String {
        var result = 0
        input.forEachIndexed{ index , item ->
            if(index>0){
                if(item > input[index-1]){
                    result +=1
                }
            }
        }
        return result.toString()
    }
}
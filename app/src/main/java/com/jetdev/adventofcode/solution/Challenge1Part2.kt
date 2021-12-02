package com.jetdev.adventofcode.solution

object Challenge1Part2 {
    fun resolve(input: List<String>): String {
        val sortedArray = mutableListOf<Int>()
        var result = 0
        input.forEachIndexed{ index , item ->
            if(index>0){
                if(input.getOrNull(index+1) != null){
                    val sumOfThreeItems = input.get(index-1).toInt() + item.toInt() + input.get(index+1).toInt()
                    sortedArray.add(sumOfThreeItems)
                }
            }
        }

        sortedArray.forEachIndexed{ index , item ->
            if(index>0){
                if(item > sortedArray[index-1]){
                    result +=1
                }
            }
        }
        return result.toString()
    }
}
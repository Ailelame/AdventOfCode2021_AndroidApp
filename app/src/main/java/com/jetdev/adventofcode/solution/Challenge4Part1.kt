package com.jetdev.adventofcode.solution

object Challenge4Part1 {

    fun resolve(input: List<String>): String {
        val cleanInput = input.filter { it.isNotBlank() }
        val chunckedInput = cleanInput.chunked(5)
        val matrixList = mutableListOf<MutableList<List<String>>>()
        chunckedInput.forEachIndexed { index, list ->
            val currentMatrix = mutableListOf<List<String>>()
            list.map { item -> // item is like "48 69 68 49 13"
                currentMatrix.add(item.replace(" ", ",").split(',').filterNot { it.isEmpty() })
            }
            matrixList.add(currentMatrix)
        }

        var winningMatrix : MutableList<List<String>>? = null
        val knownNumbers = mutableListOf<String>()

        while (winningMatrix == null){
            knownNumbers.add(bingoNumber[knownNumbers.size])
            matrixList.forEach {
                if(knownNumbers.size > 5){
                    if(it.validateGrid(knownNumbers)){
                        winningMatrix = it
                    }
                }
            }
        }
        var uncheckedValuesSum = 0
        winningMatrix?.map { list ->
            list.map {
                if (!knownNumbers.contains(it)){
                    uncheckedValuesSum += it.toInt()
                }
            }
        }

        return (knownNumbers.last().toInt() * uncheckedValuesSum).toString()
    }

    fun MutableList<List<String>>.validateGrid(knownNumbers: List<String>): Boolean {
        // check rows
        this.forEach {
            if(knownNumbers.containsAll(it))
                return true
        }
        // check columns
        val columnList = mutableListOf<List<String>>()
        for (i in 0..4){
           columnList.add(this.map { it[i] })
        }
        columnList.forEach {
            if(knownNumbers.containsAll(it))
                return true
        }
        // diagonals
        val firstDiagonal = listOf(this[0][0], this[1][1], this[2][2],this[3][3],this[4][4])
        val secondDiagonal = listOf(this[0][4], this[1][3], this[2][2],this[3][1],this[4][0])
        if(knownNumbers.containsAll(firstDiagonal) || knownNumbers.containsAll(secondDiagonal))
            return true

        return false
    }


    val bingoNumber = listOf(
        42,
        44,
        71,
        26,
        70,
        92,
        77,
        45,
        6,
        18,
        79,
        54,
        31,
        34,
        64,
        32,
        16,
        55,
        81,
        11,
        90,
        10,
        21,
        87,
        0,
        84,
        8,
        23,
        1,
        12,
        60,
        20,
        57,
        68,
        61,
        82,
        49,
        59,
        22,
        2,
        63,
        33,
        50,
        39,
        28,
        30,
        88,
        41,
        69,
        72,
        98,
        73,
        7,
        65,
        53,
        35,
        96,
        67,
        36,
        4,
        51,
        75,
        24,
        86,
        97,
        85,
        66,
        29,
        74,
        40,
        93,
        58,
        9,
        62,
        95,
        91,
        80,
        99,
        14,
        19,
        43,
        37,
        27,
        56,
        94,
        25,
        83,
        48,
        17,
        38,
        78,
        15,
        52,
        76,
        5,
        13,
        46,
        89,
        47,
        3
    ).map { it.toString() }
}
package com.jetdev.adventofcode.screen.allchallenges

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetdev.adventofcode.R
import com.jetdev.adventofcode.model.AoCDailyChallenge
import com.jetdev.adventofcode.model.Challenge
import com.jetdev.adventofcode.solution.*
import com.jetdev.adventofcode.utils.extractListFromRawFolder
import org.joda.time.DateTime

class AllChallengesViewModel(private val appContext: Context) : ViewModel() {

    private val _allChallengesList = MutableLiveData<List<AoCDailyChallenge>>()
    val allChallengesList: LiveData<List<AoCDailyChallenge>> = _allChallengesList


    fun loadData() {
        populateArrayWithDecemberDays()
    }


    private fun populateArrayWithDecemberDays() {
        var startDate = DateTime(2021, 12, 1, 0, 0)
        val endDate = DateTime(2021, 12, 25, 0, 0)
        val allDates = mutableListOf<AoCDailyChallenge>()
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            val challengeList = createChallenge(startDate)
            allDates.add(
                AoCDailyChallenge(
                    startDate.dayOfMonth().asText.toInt(),
                    startDate,
                    challengeList,
                    startDate.isBefore(DateTime.now()) || startDate.isEqual(DateTime.now())
                )
            )
            startDate = startDate.plusDays(1)
        }
        _allChallengesList.postValue(allDates)
    }

    private fun createChallenge(startDate: DateTime): List<Challenge?> {
        val currentDay = startDate.dayOfMonth().asText
        val firstChallenge = getChallengeSolver(currentDay.toInt(), 1)
        val secondChallenge = getChallengeSolver(currentDay.toInt(), 2)

        return listOf(firstChallenge, secondChallenge)
    }


    @SuppressLint("ResourceType")
    private fun getChallengeSolver(challengeNumber: Int, part: Int): Challenge? {
        val firstDayInput = appContext.resources.extractListFromRawFolder(R.raw.day1)
        val secondDayInput = appContext.resources.extractListFromRawFolder(R.raw.day2)
        val thirdDayInput = appContext.resources.extractListFromRawFolder(R.raw.day3)

        return when {
            challengeNumber == 1 && part == 1 -> Challenge(Challenge1Part1.resolve(firstDayInput), "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge1Part1.kt")
            challengeNumber == 1 && part == 2 -> Challenge(Challenge1Part2.resolve(firstDayInput), "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge1Part2.kt")
            challengeNumber == 2 && part == 1 -> Challenge(Challenge2Part1.resolve(secondDayInput), "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge2Part1.kt")
            challengeNumber == 2 && part == 2 -> Challenge(Challenge2Part2.resolve(secondDayInput), "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge2Part2.kt")
            challengeNumber == 3 && part == 1 -> Challenge(Challenge3Part1.resolve(thirdDayInput), "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge3Part1.kt")
            challengeNumber == 3 && part == 2 -> Challenge(Challenge3Part2.resolve(thirdDayInput), "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge3Part2.kt")

            else -> null
        }
    }


}
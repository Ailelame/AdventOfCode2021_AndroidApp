package com.jetdev.adventofcode.screen.solution

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetdev.adventofcode.model.AoCDailyChallenge
import com.jetdev.adventofcode.remote.CodeDownloaderManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChallengeSolutionViewModel(private val codeDownloaderManager: CodeDownloaderManager) :
    ViewModel() {

    private val _firstChallengeCode = MutableLiveData<String>()
    val firstChallengeCode: LiveData<String> = _firstChallengeCode
    private val _secondChallengeCode = MutableLiveData<String>()
    val secondChallengeCode: LiveData<String> = _secondChallengeCode



    fun loadData(item: AoCDailyChallenge?) {
        viewModelScope.launch(Dispatchers.IO) {
            val firstChallenge = item?.challenge?.firstOrNull()
            val secondChallenge = item?.challenge?.getOrNull(1)
            firstChallenge?.let {
                _firstChallengeCode.postValue(codeDownloaderManager.getCodeFromUrl(getUrlFromChallenge(item.challengeNumber, 1)))
            }
            secondChallenge?.let {
                _secondChallengeCode.postValue(codeDownloaderManager.getCodeFromUrl(getUrlFromChallenge(item.challengeNumber, 2)))
            }

        }
    }

    private fun getUrlFromChallenge(challengeNumber : Int, part : Int) : String {
        return "AdventOfCode2021_AndroidApp/master/app/src/main/java/com/jetdev/adventofcode/solution/Challenge${challengeNumber}Part${part}.kt"
    }
}
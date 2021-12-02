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
                _firstChallengeCode.postValue(codeDownloaderManager.getCodeFromUrl("adc10185fcbf10b281287f0de56c78a1/raw/c620f4ec2c7411cad300917e655c3c9119ea6ae3/AoC%2520day%25201"))
            }
            secondChallenge?.let {
                _secondChallengeCode.postValue(codeDownloaderManager.getCodeFromUrl("adc10185fcbf10b281287f0de56c78a1/raw/c620f4ec2c7411cad300917e655c3c9119ea6ae3/AoC%2520day%25201"))
            }

        }
    }
}
package com.jetdev.adventofcode.screen.exercise

import android.os.Bundle
import android.view.View
import com.jetdev.adventofcode.base.BaseFragment
import com.jetdev.adventofcode.databinding.ChallengeExerciseFragmentBinding
import com.jetdev.adventofcode.model.AoCDailyChallenge

class ChallengeExerciseFragment :
    BaseFragment<ChallengeExerciseFragmentBinding>(ChallengeExerciseFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val challenge = arguments?.getParcelable<AoCDailyChallenge>("aocChallenge")
        binding.webview.loadUrl("https://adventofcode.com/2021/day/${challenge?.challengeNumber}")
    }

}
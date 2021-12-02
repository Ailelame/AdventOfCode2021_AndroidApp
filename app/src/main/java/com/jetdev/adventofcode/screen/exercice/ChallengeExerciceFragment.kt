package com.jetdev.adventofcode.screen.exercice

import android.os.Bundle
import android.view.View
import com.jetdev.adventofcode.base.AoCApplication
import com.jetdev.adventofcode.base.BaseFragment
import com.jetdev.adventofcode.databinding.ChallengeExerciceFragmentBinding
import com.jetdev.adventofcode.model.AoCDailyChallenge

class ChallengeExerciceFragment : BaseFragment<ChallengeExerciceFragmentBinding>(ChallengeExerciceFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val challenge = arguments?.getParcelable<AoCDailyChallenge>("aocChallenge")
        binding.webview.loadUrl("https://adventofcode.com/2021/day/${challenge?.challengeNumber}")
    }

}
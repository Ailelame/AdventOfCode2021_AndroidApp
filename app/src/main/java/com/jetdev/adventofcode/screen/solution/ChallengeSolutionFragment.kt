package com.jetdev.adventofcode.screen.solution

import android.os.Bundle
import android.view.View
import com.jetdev.adventofcode.base.BaseFragment
import com.jetdev.adventofcode.databinding.ChallengeSolutionFragmentBinding
import com.jetdev.adventofcode.model.AoCDailyChallenge
import org.koin.android.ext.android.inject

class ChallengeSolutionFragment :
    BaseFragment<ChallengeSolutionFragmentBinding>(ChallengeSolutionFragmentBinding::inflate) {

    private val viewModel: ChallengeSolutionViewModel by inject()

    private var challenge: AoCDailyChallenge? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        challenge = arguments?.getParcelable("aocChallenge")

        challenge?.let {
            viewModel.loadData(it)

            val firstAnswer = it.challenge.firstOrNull()
            val secondAnswer = it.challenge.getOrNull(1)
            firstAnswer?.let {
                binding.answerInput1.text = it.solution
            }
            secondAnswer?.let {
                binding.answerInput2.text = it.solution
            }
        }

    }

    private fun initObservers() {
        viewModel.firstChallengeCode.observe(viewLifecycleOwner) {
            binding.solutionInput1.bindSyntaxHighlighter(
                formattedSourceCode = it,
                language = "kotlin",
                showLineNumbers = true
            )
        }
        viewModel.secondChallengeCode.observe(viewLifecycleOwner) {
            binding.solutionInput2.bindSyntaxHighlighter(
                formattedSourceCode = it,
                language = "kotlin",
                showLineNumbers = true
            )
        }

    }
}


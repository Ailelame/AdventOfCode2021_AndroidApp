package com.jetdev.adventofcode.screen.allchallenges

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetdev.adventofcode.base.BaseFragment
import com.jetdev.adventofcode.databinding.AllChallengesFragmentLayoutBinding
import org.koin.android.ext.android.inject

class AllChallengesFragment :
    BaseFragment<AllChallengesFragmentLayoutBinding>(AllChallengesFragmentLayoutBinding::inflate) {

    private val viewModel: AllChallengesViewModel by inject()

    lateinit var adapter: AllChallengesAdapter

    private var motionLayoutProgress = 0f


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AllChallengesAdapter()
        binding.challengeRv.layoutManager = LinearLayoutManager(context)
        binding.challengeRv.adapter = adapter

        binding.motionLayout.progress = motionLayoutProgress

        initObservers()
        viewModel.loadData()
    }

    override fun onPause() {
        super.onPause()
        motionLayoutProgress = binding.motionLayout.progress
    }

    private fun initObservers() {
        viewModel.allChallengesList.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        adapter.itemClicked.observe(viewLifecycleOwner) {
            val action = it.contentIfNotHandled
            when (action) {
                is AllChallengesAdapter.AllChallengeActions.SolutionClicked -> {
                    val navAction =
                        AllChallengesFragmentDirections.actionAllChallengesFragmentToChallengeSolutionFragment(
                            action.challenge
                        )
                    findNavController().navigate(navAction)
                }
                is AllChallengesAdapter.AllChallengeActions.ExerciseClicked -> {
                    val navAction =
                        AllChallengesFragmentDirections.actionAllChallengesFragmentToChallengeDetailFragment(
                            action.challenge
                        )
                    findNavController().navigate(navAction)
                }
                is AllChallengesAdapter.AllChallengeActions.BeforeDateClicked -> {
                    Toast.makeText(
                        context,
                        "Ne soyez pas si h??tif ma??tre Touque !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AllChallengesAdapter.AllChallengeActions.NoSolutionClicked -> {
                    Toast.makeText(
                        context,
                        "Pas encore de solution ??\\_(???)_/??",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    // Do nothing
                }
            }
        }

    }


}
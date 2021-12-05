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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AllChallengesAdapter()
        binding.challengeRv.layoutManager = LinearLayoutManager(context)
        binding.challengeRv.adapter = adapter

        initObservers()
        viewModel.loadData()
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
                        "Ne soyez pas si hâtif maître Touque !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AllChallengesAdapter.AllChallengeActions.NoSolutionClicked -> {
                    Toast.makeText(
                        context,
                        "Pas encore de solution ¯\\_(ツ)_/¯",
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
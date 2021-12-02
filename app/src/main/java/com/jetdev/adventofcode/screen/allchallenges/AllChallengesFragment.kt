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

        adapter.exerciceClicked.observe(viewLifecycleOwner) {
            val challenge = it.contentIfNotHandled
            challenge?.let {
                val action =
                    AllChallengesFragmentDirections.actionAllChallengesFragmentToChallengeDetailFragment(
                        it
                    )
                findNavController().navigate(action)
            }

        }
        adapter.solutionClicked.observe(viewLifecycleOwner) {
            val challenge = it.contentIfNotHandled
            challenge?.let {
                val action =
                    AllChallengesFragmentDirections.actionAllChallengesFragmentToChallengeSolutionFragment(it)
                findNavController().navigate(action)
            }
        }
        adapter.disabledClicked.observe(viewLifecycleOwner) {
            val challenge = it.contentIfNotHandled
            challenge?.let {
               Toast.makeText(context, "Ne soyez pas si hâtif maître Touque !", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
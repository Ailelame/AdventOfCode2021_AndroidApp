package com.jetdev.adventofcode.screen.allchallenges

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jetdev.adventofcode.R
import com.jetdev.adventofcode.databinding.AllChallengesViewHolderBinding
import com.jetdev.adventofcode.model.AoCDailyChallenge
import com.jetdev.adventofcode.utils.Event
import java.util.*

class AllChallengesViewHolder(
    val binding: AllChallengesViewHolderBinding,
    private val clickListener: MutableLiveData<Event<AllChallengesAdapter.AllChallengeActions>>
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: AoCDailyChallenge) {
        binding.title.text =
            "${item.date.dayOfMonth().asText} ${item.date.monthOfYear().getAsText(Locale.FRENCH)}"

        if (!item.isEnabled) {
            binding.title.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.primaryDarkColor
                )
            )
            listOf(binding.solution, binding.exercise).map {
                it.disableButton()
                it.setOnClickListener {
                    clickListener.postValue(Event(AllChallengesAdapter.AllChallengeActions.BeforeDateClicked()))
                }
            }
        } else {
            binding.title.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.secondaryDarkColor
                )
            )
            binding.exercise.enableButton()
            if (item.challenge.any { !it?.solution.isNullOrBlank() }) {
                binding.solution.enableButton()
            } else {
                binding.solution.disableButton()
            }

            binding.exercise.setOnClickListener {
                clickListener.postValue(Event(AllChallengesAdapter.AllChallengeActions.ExerciseClicked(item)))
            }
            binding.solution.setOnClickListener {
                if(item.challenge.any { it != null })
                    clickListener.postValue(Event(AllChallengesAdapter.AllChallengeActions.SolutionClicked(item)))
                else
                    clickListener.postValue(Event(AllChallengesAdapter.AllChallengeActions.NoSolutionClicked()))
            }
        }
    }

    private fun TextView.disableButton() {
        setTextColor(ContextCompat.getColor(binding.root.context, R.color.primaryDarkColor))
        setBackgroundResource(R.drawable.background_button_disabled)
    }

    private fun TextView.enableButton() {
        setTextColor(ContextCompat.getColor(binding.root.context, R.color.secondaryDarkColor))
        setBackgroundResource(R.drawable.background_button)
    }

}
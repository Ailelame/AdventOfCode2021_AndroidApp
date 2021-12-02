package com.jetdev.adventofcode.screen.allchallenges

import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jetdev.adventofcode.R
import com.jetdev.adventofcode.databinding.AllChallengesViewHolderBinding
import com.jetdev.adventofcode.model.AoCDailyChallenge
import com.jetdev.adventofcode.utils.Event
import java.util.*

class AllChallengesViewHolder(val binding: AllChallengesViewHolderBinding,
                              private val exerciceClickListener : MutableLiveData<Event<AoCDailyChallenge>>,
                              private val solutionClickListener : MutableLiveData<Event<AoCDailyChallenge>>,
                              private val disabledClickListener : MutableLiveData<Event<AoCDailyChallenge>>
                              ) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: AoCDailyChallenge) {
        binding.title.text = "${item.date.dayOfMonth().asText} ${item.date.monthOfYear().getAsText(Locale.FRENCH)}"

        if(!item.isEnabled){
            binding.title.setTextColor(ContextCompat.getColor(binding.root.context, R.color.primaryDarkColor))
            listOf(binding.solution, binding.exercice).map {
                it.setTextColor(ContextCompat.getColor(binding.root.context, R.color.primaryDarkColor))
                it.setBackgroundResource(R.drawable.background_button_disabled)
                it.setOnClickListener {
                    disabledClickListener.postValue(Event(item))
                }
            }
        } else {
            binding.exercice.setOnClickListener {
                exerciceClickListener.postValue(Event(item))
            }
            binding.solution.setOnClickListener {
                solutionClickListener.postValue(Event(item))
            }
        }
    }

}
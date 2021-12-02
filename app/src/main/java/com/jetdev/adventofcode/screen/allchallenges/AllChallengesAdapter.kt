package com.jetdev.adventofcode.screen.allchallenges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jetdev.adventofcode.databinding.AllChallengesViewHolderBinding
import com.jetdev.adventofcode.model.AoCDailyChallenge
import com.jetdev.adventofcode.utils.Event

class AllChallengesAdapter : RecyclerView.Adapter<AllChallengesViewHolder>() {

    private val list = mutableListOf<AoCDailyChallenge>()

    private val _exerciceClicked = MutableLiveData<Event<AoCDailyChallenge>>()
    val exerciceClicked : LiveData<Event<AoCDailyChallenge>> = _exerciceClicked

    private val _solutionClicked = MutableLiveData<Event<AoCDailyChallenge>>()
    val solutionClicked : LiveData<Event<AoCDailyChallenge>> = _solutionClicked

    private val _disabledClicked = MutableLiveData<Event<AoCDailyChallenge>>()
    val disabledClicked : LiveData<Event<AoCDailyChallenge>> = _disabledClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChallengesViewHolder {
        val itemBinding = AllChallengesViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AllChallengesViewHolder(itemBinding, _exerciceClicked, _solutionClicked, _disabledClicked)
    }

    override fun onBindViewHolder(holder: AllChallengesViewHolder, position: Int) {
        val item = list.getOrNull(position)
        item?.let {
            holder.onBind(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(newList : List<AoCDailyChallenge>){
        this.list.clear()
        this.list.addAll(newList)
        notifyDataSetChanged()
    }
}
package com.jetdev.adventofcode.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime

@Parcelize
data class AoCDailyChallenge(
    val challengeNumber : Int,
    val date: DateTime,
    val challenge: List<Challenge?>,
    val isEnabled : Boolean
) : Parcelable
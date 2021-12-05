package com.jetdev.adventofcode.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Challenge(
    val solution :String?
) : Parcelable
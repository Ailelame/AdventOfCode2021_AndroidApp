package com.jetdev.adventofcode.di

import com.jetdev.adventofcode.screen.allchallenges.AllChallengesViewModel
import com.jetdev.adventofcode.screen.solution.ChallengeSolutionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory { AllChallengesViewModel(androidContext()) }
    factory { ChallengeSolutionViewModel(get()) }
}
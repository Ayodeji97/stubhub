package com.financials.stubhubevents.presentation.main

import com.financials.stubhubevents.business.model.sample.WelcomeEvent

sealed class MainViewState {
    object Loading : MainViewState()
    data class Success (val events : List<WelcomeEvent>) : MainViewState()
    object Empty : MainViewState()
}

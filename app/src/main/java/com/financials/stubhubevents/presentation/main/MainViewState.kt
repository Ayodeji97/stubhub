package com.financials.stubhubevents.presentation.main

import com.financials.stubhubevents.business.model.Event

sealed class MainViewState {
    object Loading : MainViewState()
    data class Success (val events : List<Event>) : MainViewState()
    object Empty : MainViewState()
}

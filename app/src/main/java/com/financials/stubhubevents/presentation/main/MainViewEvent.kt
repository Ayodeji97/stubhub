package com.financials.stubhubevents.presentation.main


// Event that user can perform on the main screen
sealed class MainViewEvent {
    object GetAllEvent: MainViewEvent()

    data class GetEventByCityName(
        val cityQuery: String
    ) : MainViewEvent()

    data class GetEventByPriceAmount(
        val priceQuery: String
    ) : MainViewEvent()

    data class GetEventByCityNameAndPriceAmount(
        val cityQuery: String,
        val priceQuery: String
    ) : MainViewEvent()

}

package com.financials.stubhubevents.business.model.sample

data class Welcome(
    val id: Long,
    val name: String,
    val events: List<WelcomeEvent>,
    val children: List<Welcome>
)

data class WelcomeEvent(
    val id: Long,
    val name: String,
    val venueName: String,
    val city: String,
    val price: Long,
    val distanceFromVenue: Double? = null,
    val date: String,
    val distanceFromValue: Double? = null
)

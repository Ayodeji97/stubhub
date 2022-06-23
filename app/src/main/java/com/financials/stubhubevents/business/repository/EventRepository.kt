package com.financials.stubhubevents.business.repository

import com.financials.stubhubevents.business.model.sample.Welcome
import com.financials.stubhubevents.business.model.sample.WelcomeEvent

interface EventRepository {

    fun getAllEvent () : Welcome
    fun recursiveEvents(welcome: Welcome) : List<WelcomeEvent>
    fun getEventByCityName (cityQuery : String, events : List<WelcomeEvent>) : List<WelcomeEvent>
    fun getEventByPriceAmount (priceQuery : String, events : List<WelcomeEvent>): List<WelcomeEvent>
    fun getEventByCityNameAndPriceAmount (cityQuery: String, priceQuery: String,
                                          events : List<WelcomeEvent> ) : List<WelcomeEvent>

}
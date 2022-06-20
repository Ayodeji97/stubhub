package com.financials.stubhubevents.business.repository

import android.content.Context
import com.financials.stubhubevents.business.model.Event

interface EventRepository {
    fun getAllEvent () : List<Event>
    fun getEventByCityName (cityQuery : String) : List<Event>
    fun getEventByPriceAmount (priceQuery : String) : List<Event>
    fun getEventByCityNameAndPriceAmount (cityQuery: String, priceQuery: String) : List<Event>
}
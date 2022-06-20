package com.financials.stubhubevents.business.repository

import android.content.Context
import com.financials.stubhubevents.business.model.Event
import com.financials.stubhubevents.business.utils.Constants.JSON_FILE_NAME
import com.financials.stubhubevents.business.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val context: Context
) : EventRepository {


    override fun getAllEvent(): List<Event> {
        val jsonFileToString = getJsonDataFromAsset(context, JSON_FILE_NAME) ?: return emptyList()

        val eventListType = object : TypeToken<List<Event>>() {}.type
        return gson.fromJson(jsonFileToString, eventListType)
    }

    override fun getEventByCityName(cityQuery: String): List<Event> {
        val getAllEvents = getAllEvent()
        val filterByCityName = getAllEvents.filter { event ->
            event.city.lowercase().contains(cityQuery.lowercase())
        }
        return filterByCityName
    }

    override fun getEventByPriceAmount(priceQuery: String): List<Event> {
        val getAllEvents = getAllEvent()
        val filterByPrice = getAllEvents.filter { event ->
            event.price <= priceQuery.toInt()
        }
        return filterByPrice
    }

    override fun getEventByCityNameAndPriceAmount(
        cityQuery: String,
        priceQuery: String
    ): List<Event> {
        val cityQueries = getEventByCityName(cityQuery)
        val filterByCityAndPrice = cityQueries.filter { event ->
            event.price <= priceQuery.toInt()
        }
        return filterByCityAndPrice
    }
}
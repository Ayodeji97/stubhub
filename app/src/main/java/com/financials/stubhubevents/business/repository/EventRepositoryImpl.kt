package com.financials.stubhubevents.business.repository

import android.content.Context
import android.util.Log
import com.financials.stubhubevents.business.model.sample.Welcome
import com.financials.stubhubevents.business.model.sample.WelcomeEvent
import com.financials.stubhubevents.business.utils.Constants.JSON_FILE_NEW_NAME
import com.financials.stubhubevents.business.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val context: Context
) : EventRepository {


   private var eventList: MutableList<WelcomeEvent> = arrayListOf<WelcomeEvent>()

    override fun getAllEvent(): Welcome {
        val jsonFileToString = getJsonDataFromAsset(context, JSON_FILE_NEW_NAME)

        val eventListType = object : TypeToken<Welcome>() {}.type

        return gson.fromJson(jsonFileToString, eventListType)
    }


    override fun getEventByCityName(cityQuery: String, events : List<WelcomeEvent>): List<WelcomeEvent> {
        val eventList = getEventList()
        val filterByCityName = eventList.filter { event ->
            event.city.lowercase().contains(cityQuery.lowercase())
        }
        return filterByCityName.distinct()
    }

    override fun getEventByPriceAmount(priceQuery: String, events : List<WelcomeEvent>): List<WelcomeEvent> {
        val eventList = getEventList()
        val filterByPrice = eventList.filter { event ->
            event.price <= priceQuery.toInt()
        }
        return filterByPrice.distinct()
    }

    override fun getEventByCityNameAndPriceAmount(
        cityQuery: String,
        priceQuery: String,
        events : List<WelcomeEvent>
    ): List<WelcomeEvent> {
        val eventList = getEventList()
        val cityQueries = getEventByCityName(cityQuery, eventList)
        val filterByCityAndPrice = cityQueries.filter { event ->
            event.price <= priceQuery.toInt()
        }
        return filterByCityAndPrice.distinct()
    }

    private fun getEventList () : List<WelcomeEvent> {
        val welcome = getAllEvent()
        return recursiveEvents(welcome)
    }

   override fun recursiveEvents(welcome: Welcome) : List<WelcomeEvent> {
        if (welcome.children.isEmpty()) {
            eventList.addAll(welcome.events)
        }

        for (child in welcome.children) {
            recursiveEvents(child)
        }
        return eventList.distinct()
    }

}
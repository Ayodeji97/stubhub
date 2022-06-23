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

        Log.d("Events", "Events:$eventListType")

        return gson.fromJson(jsonFileToString, eventListType)
    }


    override fun getEventByCityName(cityQuery: String, events : List<WelcomeEvent>): List<WelcomeEvent> {
        val filterByCityName = events.filter { event ->
            event.city.lowercase().contains(cityQuery.lowercase())
        }
        return filterByCityName
    }

    override fun getEventByPriceAmount(priceQuery: String, events : List<WelcomeEvent>): List<WelcomeEvent> {
        val filterByPrice = events.filter { event ->
            event.price <= priceQuery.toInt()
        }
        return filterByPrice
    }

    override fun getEventByCityNameAndPriceAmount(
        cityQuery: String,
        priceQuery: String,
        events : List<WelcomeEvent>
    ): List<WelcomeEvent> {
        val cityQueries = getEventByCityName(cityQuery, events)
        val filterByCityAndPrice = cityQueries.filter { event ->
            event.price <= priceQuery.toInt()
        }
        return filterByCityAndPrice
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
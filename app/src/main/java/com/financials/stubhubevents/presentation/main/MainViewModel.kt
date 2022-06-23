package com.financials.stubhubevents.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.financials.stubhubevents.business.model.sample.Welcome
import com.financials.stubhubevents.business.model.sample.WelcomeEvent
import com.financials.stubhubevents.business.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel(){

    private var welcomeEventList : List<WelcomeEvent> = mutableListOf()
    lateinit var welcome : Welcome
    private var _mainViewState = MutableStateFlow<MainViewState>(MainViewState.Loading)
    val mainViewState = _mainViewState.asStateFlow()

    init {
        getAllEventsNew()
    }

    fun onTriggerEvent(event: MainViewEvent) {
        when(event) {
            is MainViewEvent.GetAllEvent -> {getAllEventsNew()}
            is MainViewEvent.GetEventByCityName -> {
               getEventByCityName(event.cityQuery)
            }
            is MainViewEvent.GetEventByPriceAmount -> {
                getEventByPriceAmount(event.priceQuery)
            }
            is MainViewEvent.GetEventByCityNameAndPriceAmount -> {
                getEventByCityNameAndPriceAmount(event.cityQuery, event.priceQuery)
            }

        }
    }



    private fun getAllEventsNew() {
        welcome = eventRepository.getAllEvent()
         welcomeEventList = eventRepository.recursiveEvents(welcome)
        Log.i("EventList", "$welcomeEventList")
        handleAllEventsState(welcomeEventList)
    }

    private fun getEventByCityNameAndPriceAmount(cityQuery: String, priceQuery: String) {
        val eventList = eventRepository.getEventByCityNameAndPriceAmount(cityQuery, priceQuery, welcomeEventList)
        handleAllEventsState(eventList)
    }

    private fun getEventByCityName(cityQuery : String) {
        val eventList =  eventRepository.getEventByCityName(cityQuery, welcomeEventList)
        handleAllEventsState(eventList)
    }

    private fun getEventByPriceAmount(priceQuery: String) {
       val eventList =  eventRepository.getEventByPriceAmount(priceQuery, welcomeEventList)
        handleAllEventsState(eventList)
    }

    private fun handleAllEventsState (events : List<WelcomeEvent>) {
        if (events.isNullOrEmpty()) {
            _mainViewState.value = MainViewState.Empty
        } else {
            _mainViewState.value = MainViewState.Success(events)
        }
    }
}
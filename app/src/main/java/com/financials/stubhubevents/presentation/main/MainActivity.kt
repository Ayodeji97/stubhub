package com.financials.stubhubevents.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.financials.stubhubevents.databinding.ActivityMainBinding
import com.financials.stubhubevents.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
   private val mainViewModel : MainViewModel by viewModels()

    private lateinit var ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        initRecyclerView ()
        getAllEvent ()
        subscribeObservers ()

        ui.searchBtn.setOnClickListener { decideSearchAction() }
    }

    private fun initRecyclerView () {
        mainAdapter = MainAdapter()
        ui.apply {
            recyclerViewEvent.apply {
                adapter = mainAdapter
                layoutManager = LinearLayoutManager(applicationContext)
                setHasFixedSize(true)
            }
        }
    }

    private fun subscribeObservers () {
        this.lifecycleScope.launchWhenStarted {
            mainViewModel.mainViewState.collectLatest {mainViewState->
                when(mainViewState) {
                    MainViewState.Empty -> {
                        // show empty screen
                        ui.apply {
                            progressBar.visibility = View.GONE
                            recyclerViewEvent.visibility = View.GONE
                            emptyListImg.visibility = View.VISIBLE
                            emptyListTv.visibility = View.VISIBLE
                        }
                    }
                    MainViewState.Loading -> {
                        ui.apply {
                            progressBar.visibility = View.VISIBLE
                            recyclerViewEvent.visibility = View.GONE
                            emptyListImg.visibility = View.GONE
                            emptyListTv.visibility = View.GONE
                        }
                    }
                    is MainViewState.Success -> {
                        ui.apply {
                            progressBar.visibility = View.GONE
                            recyclerViewEvent.visibility = View.VISIBLE
                            emptyListImg.visibility = View.GONE
                            emptyListTv.visibility = View.GONE
                        }
                        mainAdapter.submitList(mainViewState.events)
                    }
                }
            }
        }
    }


    private fun decideSearchAction() {
        ui.apply {
            when {
                citySearchEt.text.isNotBlank() && priceSearchEt.text.isNotBlank() -> {
                    updateUiByCityAndPriceSearch()
                }
                citySearchEt.text.isNotBlank() && priceSearchEt.text.isBlank() -> {
                    updateUiByCitySearch ()
                }
                citySearchEt.text.isBlank() && priceSearchEt.text.isNotBlank() -> {
                    updateUiByPriceSearch()
                }
                else -> {
                    getAllEvent ()
                }
            }
        }
    }

    private fun updateUiByCityAndPriceSearch() {
        val searchCityQuery = ui.citySearchEt.text.toString().trim()
        val searchPriceQuery = ui.priceSearchEt.text.toString().trim()
        mainViewModel.onTriggerEvent(MainViewEvent.GetEventByCityNameAndPriceAmount(searchCityQuery, searchPriceQuery))
    }

    private fun updateUiByCitySearch () {
        val searchQuery = ui.citySearchEt.text.toString().trim()
        mainViewModel.onTriggerEvent(MainViewEvent.GetEventByCityName(searchQuery))
    }

    private fun updateUiByPriceSearch() {
        val searchQuery = ui.priceSearchEt.text.toString().trim()
        mainViewModel.onTriggerEvent(MainViewEvent.GetEventByPriceAmount(searchQuery))
    }

    private fun getAllEvent () {
        mainViewModel.onTriggerEvent(MainViewEvent.GetAllEvent)
    }

}
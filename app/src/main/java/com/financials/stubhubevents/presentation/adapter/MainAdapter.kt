package com.financials.stubhubevents.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.financials.stubhubevents.business.model.Event
import com.financials.stubhubevents.databinding.EventListItemBinding

class MainAdapter() : ListAdapter<Event, MainViewHolder>(MainComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ui = EventListItemBinding.inflate(layoutInflater, parent, false)
        return MainViewHolder(ui)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentBinding = getItem(position)
        currentBinding.let { event ->
            holder.bind(event)
        }
    }
}
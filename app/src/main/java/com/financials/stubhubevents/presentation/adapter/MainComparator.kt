package com.financials.stubhubevents.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.financials.stubhubevents.business.model.Event

class MainComparator : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
        oldItem == newItem
}
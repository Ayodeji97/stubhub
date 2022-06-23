package com.financials.stubhubevents.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.financials.stubhubevents.business.model.sample.WelcomeEvent

class MainComparator : DiffUtil.ItemCallback<WelcomeEvent>() {
    override fun areItemsTheSame(oldItem: WelcomeEvent, newItem: WelcomeEvent): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WelcomeEvent, newItem: WelcomeEvent): Boolean =
        oldItem == newItem
}
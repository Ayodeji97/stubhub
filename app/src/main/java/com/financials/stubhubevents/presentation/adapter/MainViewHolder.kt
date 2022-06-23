package com.financials.stubhubevents.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.financials.stubhubevents.R
import com.financials.stubhubevents.business.model.sample.WelcomeEvent
import com.financials.stubhubevents.databinding.EventListItemBinding

class MainViewHolder(
    private val ui : EventListItemBinding) :
    RecyclerView.ViewHolder(ui.root) {
        fun bind (event : WelcomeEvent) {
            ui.apply {
                cityTv.text = event.city
                artisteTv.text = root.context.getString(R.string.artiste_str, event.name )
                priceTv.text = root.context.getString(R.string.dollar_sign,event.price.toString())

            }
        }
}
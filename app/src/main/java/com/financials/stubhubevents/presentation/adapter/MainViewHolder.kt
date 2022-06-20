package com.financials.stubhubevents.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.financials.stubhubevents.R
import com.financials.stubhubevents.business.model.Event
import com.financials.stubhubevents.databinding.EventListItemBinding

class MainViewHolder(
    private val ui : EventListItemBinding) :
    RecyclerView.ViewHolder(ui.root) {
        fun bind (event : Event) {
            ui.apply {
                cityTv.text = event.city
                artisteTv.text = root.context.getString(R.string.artiste_str, event.artiste )
                priceTv.text = root.context.getString(R.string.dollar_sign,event.price.toString())

            }
        }
}
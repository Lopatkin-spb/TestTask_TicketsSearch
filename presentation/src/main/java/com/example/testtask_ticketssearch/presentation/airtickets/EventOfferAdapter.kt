package com.example.testtask_ticketssearch.presentation.airtickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask_ticketssearch.databinding.ItemEventOfferBinding
import com.example.testtask_ticketssearch.domain.model.EventOfferUi

internal class EventOfferAdapter : RecyclerView.Adapter<EventOfferItem>() {

    private var currentList: List<EventOfferUi> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventOfferItem {
        val binding = ItemEventOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventOfferItem(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: EventOfferItem, position: Int) {
        val itemData = currentList[position]
        holder.setData(itemData)
    }

    fun setList(newList: List<EventOfferUi>) {
        currentList = newList
        notifyDataSetChanged()
    }

}
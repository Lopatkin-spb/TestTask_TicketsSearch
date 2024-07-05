package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask_ticketssearch.databinding.ItemTicketBinding
import com.example.testtask_ticketssearch.domain.model.TicketUi

internal class TicketAdapter : RecyclerView.Adapter<TicketItem>() {

    private var currentList: List<TicketUi> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItem {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketItem(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: TicketItem, position: Int) {
        val itemData = currentList[position]
        holder.setData(itemData)
    }

    fun setList(newList: List<TicketUi>) {
        currentList = newList
        notifyDataSetChanged()
    }

}
package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.databinding.ItemTicketBinding
import com.example.testtask_ticketssearch.domain.model.TicketUi

internal class TicketItem(
    private val binding: ItemTicketBinding,
    private val onItemClickTest: (position: Int) -> Unit,
    private val onPriceClickTest: (position: Int) -> Unit,
    private val onBadgeClickTest: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener { onItemClickTest(adapterPosition) }
        binding.textPrice.setOnClickListener { onPriceClickTest(adapterPosition) }
        binding.textBadge.setOnClickListener { onBadgeClickTest(adapterPosition) }
    }

    private var data: TicketUi? = null

    fun setData(data: TicketUi) {
        this.data = data
        updateItem()
    }

    private fun updateItem() {
        this.data?.let { ticket ->

            if (ticket.isBadgeVisible) {
                binding.textBadge.visibility = View.VISIBLE
                binding.textBadge.text = ticket.badgeText
            } else {
                binding.textBadge.visibility = View.GONE
            }

            if (ticket.price.isNotEmpty()) {
                val textWithPrice = itemView.resources.getString(R.string.text_ticket_price, ticket.price)
                binding.textPrice.text = textWithPrice
            }
            if (ticket.departureTime.isNotEmpty()) binding.textDepartureTime.text = ticket.departureTime
            if (ticket.departureAirport.isNotEmpty()) binding.textDepartureAirport.text = ticket.departureAirport
            if (ticket.arrivalTime.isNotEmpty()) binding.textArrivalTime.text = ticket.arrivalTime
            if (ticket.arrivalAirport.isNotEmpty()) binding.textArrivalAirport.text = ticket.arrivalAirport

            if (!ticket.hasTransfer) {
                val text = itemView.resources.getString(R.string.text_travel_time_without_transfer)
                binding.textDescription.text = text
            } else {
                binding.textDescription.text = itemView.resources.getString(R.string.text_travel_time)
            }
        }
    }

}
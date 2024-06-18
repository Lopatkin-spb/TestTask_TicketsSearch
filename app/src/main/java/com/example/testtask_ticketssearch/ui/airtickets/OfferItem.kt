package com.example.testtask_ticketssearch.ui.airtickets

import androidx.recyclerview.widget.RecyclerView
import com.example.testtask_ticketssearch.databinding.ItemOfferBinding

class OfferItem(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {

    private var data: OfferUi? = null

    fun setData(data: OfferUi) {
        this.data = data
        this.data?.let { updateItem() }
    }

    private fun updateItem() {
        binding.textTitle.text = data?.title
        binding.textTown.text = data?.town

        val textWithPrice = "от ${data?.price} ₽"
        binding.textPrice.text = textWithPrice
    }

}
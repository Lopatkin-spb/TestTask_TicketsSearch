package com.example.testtask_ticketssearch.presentation.airtickets

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask_ticketssearch.databinding.ItemEventOfferBinding
import com.example.testtask_ticketssearch.domain.model.EventOfferUi

internal class EventOfferItem(
    private val binding: ItemEventOfferBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private var data: EventOfferUi? = null

    fun setData(data: EventOfferUi) {
        this.data = data
        this.data?.let { updateItem() }
    }

    private fun updateItem() {
        binding.textTitle.text = data?.title
        binding.textTown.text = data?.town

        val textWithPrice = "от ${data?.price} ₽"
        binding.textPrice.text = textWithPrice

        data?.url?.let { url ->
            Glide.with(itemView)
                .load(url)
                .into(binding.imagePhoto)
        }
    }

}
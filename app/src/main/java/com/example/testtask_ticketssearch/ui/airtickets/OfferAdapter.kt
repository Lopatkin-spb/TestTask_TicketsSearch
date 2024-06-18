package com.example.testtask_ticketssearch.ui.airtickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask_ticketssearch.databinding.ItemOfferBinding

class OfferAdapter : RecyclerView.Adapter<OfferItem>() {

    private var currentList: List<OfferUi> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferItem {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferItem(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: OfferItem, position: Int) {
        val itemData = currentList[position]
        holder.setData(itemData)
    }

    fun setList(newList: List<OfferUi>) {
        currentList = newList
        notifyDataSetChanged()
    }

}
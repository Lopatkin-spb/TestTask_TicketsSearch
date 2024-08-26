package com.example.ticketlist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketlist.databinding.ItemTicketBinding
import com.example.ticketlist.domain.model.TicketUi

internal class TicketAdapter(
    private val onItemClickTest: (model: TicketUi) -> Unit,
    private val onPriceClickTest: (model: TicketUi) -> Unit,
    private val onBadgeClickTest: (model: TicketUi) -> Unit,
) : RecyclerView.Adapter<TicketItem>() {

    private var currentList: List<TicketUi> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItem {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketItem(
            binding = binding,
            onItemClickTest = { position ->
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickTest(currentList[position])
                }
            },
            onPriceClickTest = { position ->
                if (position != RecyclerView.NO_POSITION) {
                    onPriceClickTest(currentList[position])
                }
            },
            onBadgeClickTest = { position ->
                if (position != RecyclerView.NO_POSITION) {
                    onBadgeClickTest(currentList[position])
                }
            },
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: TicketItem, position: Int) {
        val itemData = currentList[position]
        holder.setData(itemData)
    }

    fun setList(newList: List<TicketUi>) {
        val diffResult = DiffUtil.calculateDiff(TicketDiffUtilCallback(currentList, newList))
        diffResult.dispatchUpdatesTo(this)
        currentList = newList
    }

}

 class TicketDiffUtilCallback(
    private val oldList: List<TicketUi>,
    private val newList: List<TicketUi>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition))
    }

}
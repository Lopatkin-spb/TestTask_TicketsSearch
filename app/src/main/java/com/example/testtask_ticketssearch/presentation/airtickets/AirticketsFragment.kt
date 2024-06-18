package com.example.testtask_ticketssearch.presentation.airtickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask_ticketssearch.databinding.FragmentAirticketsBinding

class AirticketsFragment : Fragment() {

    private lateinit var viewModel: AirticketsViewModel
    private var _binding: FragmentAirticketsBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { OfferAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AirticketsViewModel::class.java)

        _binding = FragmentAirticketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupList()

        return root
    }

    override fun onResume() {
        super.onResume()
        uiStateListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupList() {
        binding.recyclerOffers.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerOffers.adapter = adapter
    }

    private fun uiStateListener() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            adapter.setList(uiState.offers)
        }
    }

}
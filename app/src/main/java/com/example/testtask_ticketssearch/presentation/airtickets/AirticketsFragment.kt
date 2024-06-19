package com.example.testtask_ticketssearch.presentation.airtickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask_ticketssearch.databinding.FragmentAirticketsBinding
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import javax.inject.Inject

class AirticketsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AirticketsViewModel
    private var _binding: FragmentAirticketsBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { OfferAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AirticketsViewModel::class.java)

        _binding = FragmentAirticketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupList()

        return root
    }

    override fun onResume() {
        super.onResume()
        uiStateListener()
    }

    override fun onPause() {
        super.onPause()
        viewModel.savePlaceDeparture(binding.actionPlaceDeparture.text.toString())
        viewModel.savePlaceArrival(binding.actionPlaceArrival.text.toString())
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
            uiState.placeDeparture?.let { place ->
                if (place.name.isNotEmpty()) binding.actionPlaceDeparture.setText(place.name)
            }
            uiState.placeArrival?.let { place ->
                if (place.name.isNotEmpty()) binding.actionPlaceArrival.setText(place.name)
            }
        }
    }

}
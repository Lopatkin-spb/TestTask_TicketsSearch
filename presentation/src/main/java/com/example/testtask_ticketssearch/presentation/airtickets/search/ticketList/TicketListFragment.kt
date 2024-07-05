package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask_ticketssearch.databinding.FragmentTicketListBinding
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import javax.inject.Inject

class TicketListFragment : Fragment() {

    companion object {
        const val SEARCH_PLACES_KEY = "com.example.testtask_ticketssearch.SEARCH_PLACES_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TicketListViewModel
    private var _binding: FragmentTicketListBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { TicketAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(TicketListViewModel::class.java)
        _binding = FragmentTicketListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.textSearchPlaces.text = arguments?.getString(SEARCH_PLACES_KEY)
        setupList()
        onBackAction()
        return root
    }

    private fun onBackAction() {
        binding.actionBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTicketList()
        uiStateListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupList() {
        binding.recyclerTickets.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerTickets.adapter = adapter
    }

    private fun uiStateListener() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            adapter.setList(uiState.tickets)
        }
    }

}
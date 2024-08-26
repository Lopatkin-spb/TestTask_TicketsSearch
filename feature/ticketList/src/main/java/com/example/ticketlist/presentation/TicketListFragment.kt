package com.example.ticketlist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketlist.databinding.FragmentTicketListBinding
import com.example.ticketlist.di.TicketListComponentProvider
import com.example.ticketlist.domain.model.TicketUi
import com.google.android.material.snackbar.Snackbar
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
    private val adapter by lazy {
        TicketAdapter(
            onPriceClickTest = { model -> onActionTicketPrice(model) },
            onItemClickTest = { model -> onActionTicketItem(model) },
            onBadgeClickTest = { model -> onActionTicketBadge(model) },
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as TicketListComponentProvider).provideTicketListComponent().inject(this)
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

    private fun onActionTicketItem(model: TicketUi) {
        view?.let { fragment ->
            showMessage(fragment, "onActionTicketItem ${model.id}")
        }
    }

    private fun onActionTicketPrice(model: TicketUi) {
        view?.let { fragment ->
            showMessage(fragment, "onActionTicketPrice ${model.price}")
        }
    }

    private fun onActionTicketBadge(model: TicketUi) {
        view?.let { fragment ->
            showMessage(fragment, "onActionTicketBadge ${model.badgeText}")
        }
    }

    private fun showMessage(view: View, text: String) {
        Snackbar //TODO: correct color themes
            .make(view, text, Snackbar.LENGTH_SHORT)
            .show()
    }

}
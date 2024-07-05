package com.example.testtask_ticketssearch.presentation.airtickets.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.databinding.DialogSearchBinding
import com.example.testtask_ticketssearch.domain.model.TicketOfferUi
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList.TicketListFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SearchDialog : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "com.example.testtask_ticketssearch.SearchDialog"
        private const val EXTRA_PLACE_DEPARTURE = "com.example.testtask_ticketssearch.EXTRA_PLACE_DEPARTURE"
        fun newInstance(placeDeparture: String? = null): SearchDialog {
            val dialog = SearchDialog()
            val args = Bundle().apply {
                placeDeparture?.let { data -> putString(EXTRA_PLACE_DEPARTURE, data) }
            }
            dialog.arguments = args
            return dialog
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SearchViewModel
    private var _binding: DialogSearchBinding? = null
    private val binding
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        _binding = DialogSearchBinding.inflate(inflater, container, false)
        setSearchPlaceListeners()
        setHintListeners()
        setRecommendationPlacesListeners()
        setChipsListeners()
        setBottomListeners()
        binding.textPlaceDeparture.text = arguments?.getString(EXTRA_PLACE_DEPARTURE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This all shit - setup full screen
        val defaultBottomSheetLayout: FrameLayout? =
            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)

        defaultBottomSheetLayout?.let { layout ->
            layout.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT

            val bottomSheetBehavior = BottomSheetBehavior.from(layout)
            bottomSheetBehavior.also { behavior ->
                behavior.peekHeight = resources.displayMetrics.heightPixels
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

    }

    override fun onResume() {
        super.onResume()
        uiStateListener()
        viewModel.getTicketsOffers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setSearchPlaceListeners() {
        binding.actionCleaningPlaceArrival.setOnClickListener {
            if (binding.actionPlaceArrival.text.toString().isNotEmpty()) {
                binding.actionPlaceArrival.setText("")
                viewModel.serchFieldEmpty()
            }
        }
        binding.actionChangeSearchText.setOnClickListener {
            val departure = binding.textPlaceDeparture.text
            val arrival = binding.actionPlaceArrival.text.toString()
            binding.textPlaceDeparture.setText(arrival)
            binding.actionPlaceArrival.setText(departure)
        }
    }

    private fun setHintListeners() {
        binding.includeHints.actionHintRoute.setOnClickListener { view -> showMessageWithAction(view, "Stub") }
        binding.includeHints.actionHintWhere.setOnClickListener {
            fillPlaceArrival("Куда угодно")
            viewModel.serchFieldCompleted()
        }
        binding.includeHints.actionHintWeekend.setOnClickListener { view -> showMessageWithAction(view, "Stub") }
        binding.includeHints.actionHintHotTickets.setOnClickListener { view -> showMessageWithAction(view, "Stub") }
    }

    private fun setRecommendationPlacesListeners() {
        binding.includeRecommendationPlaces.actionStub1.setOnClickListener {
            fillPlaceArrival(binding.includeRecommendationPlaces.textTown1.text)
            viewModel.serchFieldCompleted()
        }
        binding.includeRecommendationPlaces.actionStub2.setOnClickListener {
            fillPlaceArrival(binding.includeRecommendationPlaces.textTown2.text)
            viewModel.serchFieldCompleted()
        }
        binding.includeRecommendationPlaces.actionStub3.setOnClickListener {
            fillPlaceArrival(binding.includeRecommendationPlaces.textTown3.text)
            viewModel.serchFieldCompleted()
        }
    }

    private fun setChipsListeners() {
        binding.includeChips.actionBack.setOnClickListener { view -> showMessage(view, "TODO: action") }
        binding.includeChips.actionDeparture.setOnClickListener { view -> showMessage(view, "TODO: action") }
        binding.includeChips.actionPassengerClass.setOnClickListener { view -> showMessage(view, "TODO: action") }
        binding.includeChips.actionFilter.setOnClickListener { view -> showMessage(view, "TODO: action") }
    }

    private fun setBottomListeners() {
        binding.actionNavigateToShowAllTickets.setOnClickListener { view ->
            dismiss()
            val places = "${binding.textPlaceDeparture.text}-${binding.actionPlaceArrival.text}"
            findNavController().navigate(
                R.id.action_navigate_from_airtickets_to_ticket_list,
                bundleOf(Pair(TicketListFragment.SEARCH_PLACES_KEY, places))
            )
        }
        binding.actionSubscriptionToPrice.setOnClickListener { view ->
            showMessage(view, "TODO: action with switcher")
        }
    }

    private fun fillPlaceArrival(text: CharSequence) {
        binding.actionPlaceArrival.setText(text)
    }

    private fun showMessage(view: View, text: String) {
        Snackbar //TODO: correct color themes
            .make(view, text, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun showMessageWithAction(view: View, text: String) {
        Snackbar //TODO: correct color themes
            .make(view, text, Snackbar.LENGTH_SHORT)
            .setAction("На главный экран") { dismiss() }
            .show()
    }

    @SuppressLint("ResourceAsColor")
    private fun uiStateListener() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState.isArrivalCompleted) {
                setAlphaVisibility(binding.includeHints.holderHints, View.GONE)
                setAlphaVisibility(binding.includeRecommendationPlaces.holderRecommendations, View.GONE)
                setAlphaVisibility(binding.actionChangeSearchText, View.VISIBLE)
                setAlphaVisibility(binding.includeChips.holderChips, View.VISIBLE)
                setAlphaVisibility(binding.includeTicketsOffers.holder, View.VISIBLE)
                setAlphaVisibility(binding.actionNavigateToShowAllTickets, View.VISIBLE)
                setAlphaVisibility(binding.actionSubscriptionToPrice, View.VISIBLE)

            } else {
                setAlphaVisibility(binding.actionChangeSearchText, View.GONE)
                setAlphaVisibility(binding.includeChips.holderChips, View.GONE)
                setAlphaVisibility(binding.includeTicketsOffers.holder, View.GONE)
                setAlphaVisibility(binding.actionNavigateToShowAllTickets, View.GONE)
                setAlphaVisibility(binding.actionSubscriptionToPrice, View.GONE)
                setAlphaVisibility(binding.includeHints.holderHints, View.VISIBLE)
                setAlphaVisibility(binding.includeRecommendationPlaces.holderRecommendations, View.VISIBLE)

            }
            if (uiState.ticketsOffers.isNotEmpty()) {
                fillViews(uiState.ticketsOffers)
            }
        }
    }

    private fun fillViews(ticketOffers: List<TicketOfferUi>) {
        ticketOffers[0]?.let { offer ->
            if (offer.title.isNotEmpty()) binding.includeTicketsOffers.textTitle1.text = offer.title
            if (offer.price.isNotEmpty()) binding.includeTicketsOffers.textPrice1.text = offer.price
            if (offer.timeFlights.isNotEmpty()) binding.includeTicketsOffers.textTimeFlights1.text = offer.timeFlights
        }
        ticketOffers[1]?.let { offer ->
            if (offer.title.isNotEmpty()) binding.includeTicketsOffers.textTitle2.text = offer.title
            if (offer.price.isNotEmpty()) binding.includeTicketsOffers.textPrice2.text = offer.price
            if (offer.timeFlights.isNotEmpty()) binding.includeTicketsOffers.textTimeFlights2.text = offer.timeFlights
        }
        ticketOffers[2]?.let { offer ->
            if (offer.title.isNotEmpty()) binding.includeTicketsOffers.textTitle3.text = offer.title
            if (offer.price.isNotEmpty()) binding.includeTicketsOffers.textPrice3.text = offer.price
            if (offer.timeFlights.isNotEmpty()) binding.includeTicketsOffers.textTimeFlights3.text = offer.timeFlights
        }
    }

    private fun setAlphaVisibility(content: View, visibility: Int) {
        val startAlpha: Float
        val endAlpha: Float
        if (visibility == View.VISIBLE) {
            startAlpha = 0f
            endAlpha = 1f
        } else {
            startAlpha = 1f
            endAlpha = 0f
        }
        content.apply {
            alpha = startAlpha
            this.visibility = visibility
            animate()
                .alpha(endAlpha)
                .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
                .setListener(null)
        }
    }

}
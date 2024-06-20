package com.example.testtask_ticketssearch.presentation.airtickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.testtask_ticketssearch.databinding.DialogSearchBinding
import com.google.android.material.snackbar.Snackbar

class SearchDialog : DialogFragment() {

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

    private var _binding: DialogSearchBinding? = null
    private val binding
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogSearchBinding.inflate(inflater, container, false)
        setupTextRecommendations()
        setHintListeners()
        setCleaningSearchListener()
        setRecommendationListeners()
        binding.textPlaceDeparture.text = arguments?.getString(EXTRA_PLACE_DEPARTURE)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupTextRecommendations() {
        binding.actionRecommendation1.textRecommendationTown.text = "Стамбул"
        binding.actionRecommendation2.textRecommendationTown.text = "Сочи"
        binding.actionRecommendation3.textRecommendationTown.text = "Пхукет"
    }

    private fun setCleaningSearchListener() {
        binding.actionCleaningPlaceArrival.setOnClickListener {
            if (binding.actionPlaceArrival.text.toString().isNotEmpty())
                binding.actionPlaceArrival.setText("")
        }
    }

    private fun setHintListeners() {
        binding.actionHintRoute.setOnClickListener { view -> showMessage(view) }
        binding.actionHintWhere.setOnClickListener { fillPlaceArrival("Куда угодно") }
        binding.actionHintWeekend.setOnClickListener { view -> showMessage(view) }
        binding.actionHintHotTickets.setOnClickListener { view -> showMessage(view) }
    }

    private fun setRecommendationListeners() {
        binding.actionRecommendation1.holderRecommendation.setOnClickListener {
            fillPlaceArrival(binding.actionRecommendation1.textRecommendationTown.text)
        }
        binding.actionRecommendation2.holderRecommendation.setOnClickListener {
            fillPlaceArrival(binding.actionRecommendation2.textRecommendationTown.text)
        }
        binding.actionRecommendation3.holderRecommendation.setOnClickListener {
            fillPlaceArrival(binding.actionRecommendation3.textRecommendationTown.text)
        }
    }

    private fun fillPlaceArrival(text: CharSequence) {
        binding.actionPlaceArrival.setText(text)
    }

    private fun showMessage(view: View) {
        Snackbar //TODO: correct color themes
            .make(view, "Stub", Snackbar.LENGTH_SHORT)
            .setAction("На главный экран") { dismiss() }
            .show()
    }

}
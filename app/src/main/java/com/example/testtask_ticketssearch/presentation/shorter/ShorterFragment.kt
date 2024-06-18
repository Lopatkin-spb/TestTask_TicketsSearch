package com.example.testtask_ticketssearch.presentation.shorter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask_ticketssearch.databinding.FragmentShorterBinding
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import javax.inject.Inject

class ShorterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _binding: FragmentShorterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ShorterViewModel::class.java)

        _binding = FragmentShorterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStub
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
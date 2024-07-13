package com.example.testtask_ticketssearch.presentation.hotels

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import javax.inject.Inject

class HotelsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this, viewModelFactory).get(HotelsViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val text by viewModel.text.observeAsState()
                Screen(text)
            }
        }
    }

}

@Composable
private fun Screen(text: String?, modifier: Modifier = Modifier) {
    MaterialTheme {
        text?.let { text ->
            Box(modifier.fillMaxSize()) {
                Text(
                    text = text,
                    modifier = modifier.fillMaxWidth().wrapContentHeight().align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    color = Color.White, //TODO: set from theme
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    val text = "HotelsFragment"
    Screen(text)
}
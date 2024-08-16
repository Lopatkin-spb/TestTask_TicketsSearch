package com.example.testtask_ticketssearch.presentation.shorter

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.TestTask_TicketsSearch_Theme
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import javax.inject.Inject


@Stable
class ShorterDaggerContainer {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
}

@Composable
internal fun ShorterScreen(
    context: Context = LocalContext.current,
    container: ShorterDaggerContainer = remember {
        ShorterDaggerContainer().also { container ->
            (context as AppActivity).presentationComponent.inject(container)
        }
    },
    viewModel: ShorterViewModel = viewModel(factory = container.viewModelFactory),
) {
    val text by viewModel.text.observeAsState()
    Screen(text)
}

@Composable
private fun Screen(text: String?, modifier: Modifier = Modifier) {
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

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    TestTask_TicketsSearch_Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Screen(text = "ShorterFragment")
        }
    }
}
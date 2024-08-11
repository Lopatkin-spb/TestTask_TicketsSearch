package com.example.testtask_ticketssearch.presentation.airtickets.search

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.testtask_ticketssearch.R
import kotlinx.coroutines.launch


@Composable
internal fun Snackbar(
    modifier: Modifier = Modifier,
    text: String?,
    textAction: String? = null,
    onShowed: () -> Unit = {},
    onAction: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarHost(
        modifier = modifier,
        hostState = snackbarHostState,
    ) { data ->
        androidx.compose.material.Snackbar(
            contentColor = colorResource(R.color.white),
            actionColor = colorResource(R.color.blue),
            snackbarData = data,
            backgroundColor = colorResource(R.color.black),
        )
    }

    text?.let { new ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = new,
                actionLabel = textAction,
                duration = SnackbarDuration.Short,
            )
            when (result) {
                SnackbarResult.ActionPerformed -> onAction()
                SnackbarResult.Dismissed -> {}
            }
        }
        onShowed()
    }
}
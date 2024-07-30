package com.example.testtask_ticketssearch.presentation.airtickets

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


@Composable
internal fun SearchField(
    modifier: Modifier = Modifier,
    textStarting: String? = null,
    textStyle: TextStyle,
    hint: String,
    hintColor: Color,
    colorCursor: Color,
    onTextChange: (new: String) -> Unit = {},
    onKeyboardDone: () -> Unit = {},
) {

    val focusManager = LocalFocusManager.current
    val textState = remember { mutableStateOf("") }
    if (textStarting != null) {
        textState.value = textStarting
    }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colorCursor,
        backgroundColor = colorCursor,
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(
            modifier = modifier,
            value = textState.value,
            onValueChange = { newText ->
                textState.value = newText
                onTextChange(newText)
            },
            singleLine = true,
            textStyle = textStyle,
            cursorBrush = SolidColor(colorCursor),
            decorationBox = { innerTextFieldHint ->
                if (textState.value.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = hintColor,
                            fontSize = textStyle.fontSize,
                        ),
                    )
                }
                innerTextFieldHint()
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onKeyboardDone()
                },
            ),
        )
    }
}
package com.harish.tvshows.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseScreen(
    title: String, isSecondaryHeader: Boolean, onBackClick: () -> Unit,
    contentScreen:@Composable ()->Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AppHeader(title, isSecondaryHeader, onBackClick)
        contentScreen()
    }
}

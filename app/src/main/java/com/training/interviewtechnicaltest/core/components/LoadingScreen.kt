package com.training.interviewtechnicaltest.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.training.interviewtechnicaltest.ui.theme.black
import com.training.interviewtechnicaltest.ui.theme.yellow

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        CircularProgressIndicator(
            modifier = modifier
                .size(40.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}
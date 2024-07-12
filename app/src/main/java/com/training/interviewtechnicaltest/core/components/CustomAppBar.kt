package com.training.interviewtechnicaltest.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.training.interviewtechnicaltest.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    onBackButtonClick: () -> Unit = {},
    showAppBarButton: Boolean = false
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            if (showAppBarButton) {
                IconButton(onClick = { onBackButtonClick() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = yellow
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    )
}
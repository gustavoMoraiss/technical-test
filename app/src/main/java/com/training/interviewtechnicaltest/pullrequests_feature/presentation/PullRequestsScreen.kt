package com.training.interviewtechnicaltest.pullrequests_feature.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.ErrorScreen
import com.training.interviewtechnicaltest.core.components.LoadingScreen
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.components.PullRequestsContent
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.state.PullRequestsUiState
import com.training.interviewtechnicaltest.ui.theme.black
import com.training.interviewtechnicaltest.ui.theme.yellow

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRequestsScreen(
    uiState: PullRequestsUiState,
    navigateToRepositories: () -> Unit,
    retryRequest: () -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name), color = yellow
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = black),
            navigationIcon = {
                IconButton(onClick = { navigateToRepositories() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = yellow
                    )
                }
            },
        )
    }, content = {
        when (uiState) {
            is PullRequestsUiState.Loading -> {
                LoadingScreen()
            }

            is PullRequestsUiState.Error -> {
                ErrorScreen(
                    message = "Verifique sua conexão com a internet",
                    retry = { retryRequest() })
            }

            is PullRequestsUiState.SuccessPullRequestsUiState -> {
                PullRequestsContent(pullRequests = uiState.pullRequests,
                    paddingValues = it,
                    onClick = { navigateToRepositories() }
                )
            }
        }
    })


}
package com.training.interviewtechnicaltest.pullrequests_feature.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.CustomAppBar
import com.training.interviewtechnicaltest.core.components.EmptyDataScreen
import com.training.interviewtechnicaltest.core.components.ErrorScreen
import com.training.interviewtechnicaltest.core.components.LoadingScreen
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.components.PullRequestsContent
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.state.PullRequestsUiState

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PullRequestsScreen(
    uiState: PullRequestsUiState,
    navigateToRepositories: () -> Unit,
    retryRequest: () -> Unit
) {
    Scaffold(topBar = {
        CustomAppBar(
            title = stringResource(id = R.string.pull_requests_screen_title_app_bar),
            showAppBarButton = true,
            onBackButtonClick = { navigateToRepositories() }
        )
    }, content = { paddingValues ->
        when (uiState) {
            is PullRequestsUiState.Loading -> {
                LoadingScreen()
            }

            is PullRequestsUiState.Error -> {
                ErrorScreen(
                    message = stringResource(R.string.pull_requests_screen_error_message),
                    retry = { retryRequest() })
            }

            is PullRequestsUiState.EmptyData -> {
                EmptyDataScreen()
            }

            is PullRequestsUiState.SuccessPullRequestsUiState -> {
                PullRequestsContent(
                    pullRequests = uiState.pullRequests,
                    paddingValues = paddingValues,
                )
            }
        }
    })


}
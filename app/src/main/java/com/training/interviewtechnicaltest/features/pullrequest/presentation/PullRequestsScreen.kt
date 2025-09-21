package com.training.interviewtechnicaltest.features.pullrequest.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.HeaderNavigation
import com.training.interviewtechnicaltest.core.components.emptystate.EmptyDataScreen
import com.training.interviewtechnicaltest.core.components.error.ErrorScreen
import com.training.interviewtechnicaltest.core.components.loading.LoadingScreen
import com.training.interviewtechnicaltest.features.pullrequest.presentation.components.PullRequestsContent
import com.training.interviewtechnicaltest.features.pullrequest.presentation.state.PullRequestsUiState

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PullRequestsScreen(
    uiState: PullRequestsUiState,
    navigateToRepositories: () -> Unit,
    retryRequest: () -> Unit
) {
    Scaffold(topBar = {
        HeaderNavigation(
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
                EmptyDataScreen(
                    emptyText = stringResource(R.string.empty_data_screen_description)
                )
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
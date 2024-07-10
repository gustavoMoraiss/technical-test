package com.training.interviewtechnicaltest.pullrequests_feature.presentation

import android.annotation.SuppressLint
import android.provider.CalendarContract
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.ErrorScreen
import com.training.interviewtechnicaltest.core.components.LoadingView
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.components.PullRequestsContent
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.state.PullRequestsUiState
import com.training.interviewtechnicaltest.repositories_feature.presentation.components.RepositoryContent
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState
import com.training.interviewtechnicaltest.ui.theme.black
import com.training.interviewtechnicaltest.ui.theme.white
import com.training.interviewtechnicaltest.ui.theme.yellow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRequestsScreen(
    uiState: PullRequestsUiState,
    navigateToRepositories: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = yellow
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = black)
            )
        },
        content = {
            when (uiState) {
                is PullRequestsUiState.Loading -> {
                    LoadingView()
                }

                is PullRequestsUiState.Error -> {
                    ErrorScreen(
                        message = "Verifique sua conexão com a internet",
                        retry = {

                        })
                }

                is PullRequestsUiState.SuccessPullRequestsUiState -> {
                    PullRequestsContent(
                        pullRequests = uiState.pullRequests,
                        paddingValues = it,
                        onClick = { navigateToRepositories() }
                    )
                }
            }
        })


}
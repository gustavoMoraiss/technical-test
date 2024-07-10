package com.training.interviewtechnicaltest.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsScreen
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsViewModel
import com.training.interviewtechnicaltest.repositories_feature.presentation.RepositoriesScreen
import com.training.interviewtechnicaltest.repositories_feature.presentation.RepositoriesViewModel
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState

const val pullRequestsScreenRoute = "pullRequestsScreenRoute"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.pullRequestsScreen(
    navHostController: NavController
) {
    composable(pullRequestsScreenRoute) {

        val viewModel: PullRequestsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        PullRequestsScreen(
            uiState = uiState,
            navigateToRepositories = { navHostController.navigateToRepositoriesScreen() },
            retryRequest = { viewModel.getPullRequests() })

    }
}

fun NavController.navigateToPullRequestsScreen() {
    navigate(route = pullRequestsScreenRoute)
}
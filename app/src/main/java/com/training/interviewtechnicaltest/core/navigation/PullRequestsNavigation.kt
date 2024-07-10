package com.training.interviewtechnicaltest.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsScreen
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsViewModel
import com.training.interviewtechnicaltest.repositories_feature.presentation.RepositoriesScreen
import com.training.interviewtechnicaltest.repositories_feature.presentation.RepositoriesViewModel
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState

const val pullRequestsScreenRoute = "pullRequestsScreenRoute"
private const val authorArgument = "authorArgument"
private const val repoArgument = "repoArgument"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.pullRequestsScreen(
    navHostController: NavController
) {
    composable(route = "${pullRequestsScreenRoute}/{$authorArgument}/{$repoArgument}",
        arguments = listOf(
            navArgument(authorArgument) { type = NavType.StringType },
            navArgument(repoArgument) { type = NavType.StringType }
        )) { backStackEntry ->

        val authorName = backStackEntry.arguments?.getString(authorArgument)
        val repoName = backStackEntry.arguments?.getString(repoArgument)

        val viewModel: PullRequestsViewModel = hiltViewModel<PullRequestsViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        viewModel.saveValues(
            author = authorName!!,
            repo = repoName!!
        )

        viewModel.getPullRequests()

        PullRequestsScreen(
            uiState = uiState,
            navigateToRepositories = { navHostController.navigateToRepositoriesScreen() },
            retryRequest = { viewModel.getPullRequests() }
        )
    }
}

fun NavController.navigateToPullRequestsScreen(author: String, repo: String) {
    navigate(route = "${pullRequestsScreenRoute}/$author/$repo")
}
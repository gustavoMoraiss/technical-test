package com.training.interviewtechnicaltest

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsScreen
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsViewModel
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.state.PullRequestsUiState
import com.training.interviewtechnicaltest.repositories_feature.presentation.RepositoriesScreen
import com.training.interviewtechnicaltest.repositories_feature.presentation.RepositoriesViewModel
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        content = {
            NavigationGraph(navController = navController)
        }
    )
}

@Composable
private fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "Repositories"
    ) {
        composable(route = "Repositories") {

            val viewModel: RepositoriesViewModel = hiltViewModel()
            val uiState: RepositoriesState = viewModel.uiState

            RepositoriesScreen(
                uiState = uiState,
                navigateToPullRequest = {
                    navController.navigate("PullRequests")
                }
            )
        }
        composable(route = "PullRequests") {

            val viewModel: PullRequestsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            PullRequestsScreen(uiState = uiState, {})

        }
    }
}

package com.training.interviewtechnicaltest.core.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.training.interviewtechnicaltest.features.repositories.presentation.RepositoriesScreen
import com.training.interviewtechnicaltest.features.repositories.presentation.RepositoriesViewModel
import com.training.interviewtechnicaltest.features.repositories.presentation.state.RepositoriesState

const val repositoriesScreenRoute = "repositoriesScreen"

fun NavGraphBuilder.repositoriesScreen(
    navHostController: NavController
) {
    composable(repositoriesScreenRoute) {

        val viewModel: RepositoriesViewModel = hiltViewModel()
        val uiState: RepositoriesState = viewModel.uiState

        RepositoriesScreen(
            uiState = uiState,
            navHostController = navHostController
        )
    }
}

fun NavController.navigateToRepositoriesScreen() {
    navigate(route = repositoriesScreenRoute)
}
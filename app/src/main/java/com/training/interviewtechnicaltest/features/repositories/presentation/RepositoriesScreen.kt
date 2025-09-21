package com.training.interviewtechnicaltest.features.repositories.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.HeaderNavigation
import com.training.interviewtechnicaltest.features.repositories.presentation.components.RepositoryContent
import com.training.interviewtechnicaltest.features.repositories.presentation.state.RepositoriesState

@Composable
fun RepositoriesScreen(
    uiState: RepositoriesState,
    navHostController: NavController
) {
    val repositories = uiState.repositories.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HeaderNavigation(
                title = stringResource(id = R.string.repositories_screen_title_app_bar),
                showAppBarButton = false
            )
        },
        content = { paddingValues ->
            RepositoryContent(
                pagingRepositories = repositories,
                paddingValues = paddingValues,
                navHostController = navHostController
            )
        })
}
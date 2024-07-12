package com.training.interviewtechnicaltest.repositories_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.CustomAppBar
import com.training.interviewtechnicaltest.repositories_feature.presentation.components.RepositoryContent
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoriesScreen(
    uiState: RepositoriesState,
    navHostController: NavController
) {
    val repositories = uiState.repositories.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CustomAppBar(
                title = stringResource(id = R.string.repositories_screen_title_app_bar),
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
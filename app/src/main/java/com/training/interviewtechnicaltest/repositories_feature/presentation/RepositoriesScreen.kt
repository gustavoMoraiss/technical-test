package com.training.interviewtechnicaltest.repositories_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.repositories_feature.presentation.components.RepositoryContent
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState
import com.training.interviewtechnicaltest.ui.theme.black
import com.training.interviewtechnicaltest.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoriesScreen(
    uiState: RepositoriesState,
    navigateToPullRequest: () -> Unit
) {
    val repositories = uiState.repositories.collectAsLazyPagingItems()

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
        content = { paddingValues ->
            RepositoryContent(
                pagingRepositories = repositories,
                paddingValues = paddingValues,
                onClick = { navigateToPullRequest() }
            )
        })


}
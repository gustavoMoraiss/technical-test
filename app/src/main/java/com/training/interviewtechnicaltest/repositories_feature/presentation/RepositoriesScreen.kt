package com.training.interviewtechnicaltest.repositories_feature.presentation

import android.provider.CalendarContract
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.repositories_feature.presentation.components.RepositoryContent
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState
import com.training.interviewtechnicaltest.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoriesScreen(
    uiState: RepositoriesState, navigateToPullRequest: (String) -> Unit
) {
    val repositories = uiState.repositories.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.Black
                    )
                },
                )
        },
        content = { paddingValues ->
            RepositoryContent(
                pagingRepositories = repositories,
                paddingValues = paddingValues
            ) {

            }
        })


}
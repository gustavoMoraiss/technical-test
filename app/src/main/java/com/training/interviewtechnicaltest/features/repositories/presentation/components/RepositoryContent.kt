package com.training.interviewtechnicaltest.features.repositories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.error.ErrorView
import com.training.interviewtechnicaltest.core.components.loading.LoadingView
import com.training.interviewtechnicaltest.core.domain.model.RepositoryModel

@Composable
fun RepositoryContent(
    modifier: Modifier = Modifier,
    pagingRepositories: LazyPagingItems<RepositoryModel>,
    paddingValues: PaddingValues,
    navHostController: NavController
) {

    val context = LocalContext.current

    Surface(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clearAndSetSemantics {
                    contentDescription =
                        context.getString(R.string.repository_content_description_lazy_vertical_grid)
                }
        ) {
            items(pagingRepositories.itemCount) { index ->
                val repository = pagingRepositories[index]
                repository?.let { repo ->
                    RepositoryItem(
                        repository = repo,
                        navHostController = navHostController,
                    )
                }
            }

            pagingRepositories.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            ErrorView(
                                message = stringResource(id = R.string.repository_content_error_message),
                                retry = {
                                    retry()
                                })
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            ErrorView(
                                message = stringResource(id = R.string.repository_content_error_message),
                                retry = {
                                    retry()
                                })
                        }
                    }
                }
            }
        }
    }
}
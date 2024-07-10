package com.training.interviewtechnicaltest.repositories_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.training.interviewtechnicaltest.core.components.ErrorView
import com.training.interviewtechnicaltest.core.components.LoadingView
import com.training.interviewtechnicaltest.core.domain.model.Repository

@Composable
fun RepositoryContent(
    modifier: Modifier = Modifier,
    pagingRepositories: LazyPagingItems<Repository>,
    paddingValues: PaddingValues,
    onClick: () -> Unit
) {
    Box(modifier = modifier.background(Color.Black)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingRepositories.itemCount) { index ->
                val repository = pagingRepositories[index]
                repository?.let { repo ->
                    RepositoryItem(repository = repo, onItemClick = { onClick() })
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
                                message = "Verifique sua conexão com a internet",
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
                                message = "Verifique sua conexão com a internet",
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
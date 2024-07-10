package com.training.interviewtechnicaltest.pullrequests_feature.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.training.interviewtechnicaltest.core.components.ErrorScreen
import com.training.interviewtechnicaltest.core.components.LoadingView
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.domain.model.Repository

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PullRequestsContent(
    modifier: Modifier = Modifier,
    pullRequests: List<PullRequestResponse?>,
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
            pullRequests.isNotEmpty().let {
                items(pullRequests.size) { index ->
                    val pullRequest = pullRequests[index]
                    PullRequestItem(pullRequest = pullRequest)
                }
            }
        }
    }
}

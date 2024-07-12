package com.training.interviewtechnicaltest.pullrequests_feature.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.domain.model.PullRequest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PullRequestsContent(
    modifier: Modifier = Modifier,
    pullRequests: List<PullRequest?>,
    paddingValues: PaddingValues,
    onClick: () -> Unit = {}
) {

    val context = LocalContext.current

    Surface(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().clearAndSetSemantics {
                contentDescription =
                    context.getString(R.string.pull_requests_content_description_lazy_vertical_grid)
            }
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

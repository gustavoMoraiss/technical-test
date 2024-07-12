package com.training.interviewtechnicaltest.pullrequests_feature.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.AsyncAvatarImage
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.domain.model.PullRequest
import com.training.interviewtechnicaltest.core.util.UtilFunctions.formatDateAPI
import com.training.interviewtechnicaltest.ui.theme.black
import com.training.interviewtechnicaltest.ui.theme.white
import com.training.interviewtechnicaltest.ui.theme.yellow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PullRequestItem(
    pullRequest: PullRequest?,
    onItemClick: () -> Unit = {}
) {

    val context = LocalContext.current

    var expanded by remember {
        mutableStateOf(false)
    }
    OutlinedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
            .clearAndSetSemantics {
                contentDescription =
                    context.getString(R.string.pull_requests_item_description_outlined_card)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncAvatarImage(
                dataUrl = pullRequest?.head?.user?.avatarUrl ?: "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.pull_requests_item_description_avatar_image)
                    })
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = pullRequest?.title ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.pull_requests_item_description_pull_request_title)
                    }
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = String.format("Description: %s", pullRequest?.body ?: ""),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.pull_requests_item_description_pull_request_body)
                    },
                    maxLines = if (!expanded) 4 else Int.MAX_VALUE
                )

                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    text = pullRequest?.head?.user?.login ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.pull_requests_item_description_pull_request_login)
                    }

                )
                AnimatedVisibility(visible = expanded) {
                    Column {
                        HorizontalDivider(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Authored by: ${pullRequest?.head?.user?.login ?: ""}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clearAndSetSemantics {
                                contentDescription =
                                    context.getString(R.string.pull_requests_item_description_pull_request_author)
                            }
                        )

                        Text(
                            text = "Created at: ${pullRequest?.createdAt?.formatDateAPI() ?: ""}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clearAndSetSemantics {
                                contentDescription =
                                    context.getString(R.string.pull_requests_item_description_pull_request_created_at)
                            }
                        )
                    }
                }
                Icon(
                    imageVector = if (!expanded)
                        Icons.Filled.KeyboardArrowDown
                    else
                        Icons.Filled.KeyboardArrowUp,
                    contentDescription = "keyboard down",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            expanded = !expanded
                        }
                        .clearAndSetSemantics {
                            contentDescription =
                                context.getString(R.string.repository_item_description_repository_icon_button)
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }
}
package com.training.interviewtechnicaltest.repositories_feature.presentation.components

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
import androidx.compose.material.icons.filled.ForkLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.components.AsyncAvatarImage
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.core.navigation.navigateToPullRequestsScreen

@Composable
fun RepositoryItem(
    repository: Repository,
    navHostController: NavController,
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
                navHostController.navigateToPullRequestsScreen(
                    author = repository.owner?.login ?: "", repo = repository.name ?: ""
                )
            }
            .clearAndSetSemantics {
                contentDescription =
                    context.getString(R.string.repository_item_description_outlined_card)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncAvatarImage(
                dataUrl = repository.owner?.avatarUrl ?: "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.repository_item_description_avatar_image)
                    }
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = repository.name ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.repository_item_description_repository_name)
                    })

                Spacer(modifier = Modifier.size(8.dp))

                Text(text = String.format("Description: %s", repository.description ?: ""),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.repository_item_description_repository_description)
                    })

                Spacer(modifier = Modifier.size(4.dp))

                Text(text = repository.fullName ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.repository_item_description_repository_full_name)
                    }

                )
                AnimatedVisibility(visible = expanded) {
                    Column {
                        HorizontalDivider(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Authored by: ${repository.owner?.login ?: ""}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Row {
                            RepositoryRate(icon = Icons.Default.ForkLeft,
                                value = repository.forksCount ?: 0,
                                modifier = Modifier.clearAndSetSemantics {
                                    contentDescription =
                                        context.getString(R.string.repository_item_description_repository_forks_count)
                                })
                            Spacer(modifier = Modifier.size(8.dp))
                            RepositoryRate(icon = Icons.Default.Star,
                                value = repository.stargazersCount ?: 0,
                                modifier = Modifier.clearAndSetSemantics {
                                    contentDescription =
                                        context.getString(R.string.repository_item_description_repository_stars_count)
                                })
                        }
                    }
                }
                Icon(imageVector = if (!expanded) Icons.Filled.KeyboardArrowDown
                else Icons.Filled.KeyboardArrowUp,
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
package com.training.interviewtechnicaltest.repositories_feature.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.training.interviewtechnicaltest.R
import com.training.interviewtechnicaltest.core.domain.model.Repository

@Composable
fun RepositoryItem(
    repository: Repository,
    onItemClick: (String) -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick("")
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
            )
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repository.owner.avatarUrl)
                        .crossfade(true)
                        .error(R.drawable.ic_launcher_background)
                        .placeholder(R.drawable.ic_launcher_foreground).build(),
                    contentDescription = "image icon",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = repository.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = repository.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = repository.fullName,
                    style = MaterialTheme.typography.bodyMedium
                )
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(repository.description)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        HorizontalDivider(modifier = Modifier.padding(3.dp))
                        Text(
                            text = "Authored by: ${repository.owner.login}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row {
                            RepositoryRate(
                                icon = Icons.Default.Star,
                                value = repository.forksCount
                            )
                            RepositoryRate(
                                icon = Icons.Default.Star,
                                value = repository.stargazersCount
                            )
                        }
                    }
                }
                Icon(
                    imageVector = if (!expanded)
                        Icons.Filled.KeyboardArrowDown
                    else
                        Icons.Filled.KeyboardArrowUp,
                    contentDescription = "keyboard down",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }
}
package com.training.interviewtechnicaltest.features.pullrequest.data.mapper

import HeadResult
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.domain.model.PullRequest

fun List<PullRequestResponse?>.toPullRequestList(): List<PullRequest?> = map { pullRequestResult ->
    PullRequest(
        title = pullRequestResult?.title ?: "",
        body = pullRequestResult?.body ?: "",
        head = pullRequestResult?.head ?: HeadResult(),
        createdAt = pullRequestResult?.createdAt ?: ""
    )
}
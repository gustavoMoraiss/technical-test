package com.training.interviewtechnicaltest.core.domain.model

import HeadResult
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse

class PullRequestsFactory {

    companion object {
        fun create(pullRequestsType: PullRequestsType): List<PullRequestResponse> =
            when (pullRequestsType) {
                is PullRequestsType.Default -> {
                    listOf(
                        PullRequestResponse(
                            title = "fakeTitle",
                            body = "bfakeBody",
                            createdAt = "",
                            head = HeadResult()
                        ),
                        PullRequestResponse(
                            title = "fakeTitle",
                            body = "bfakeBody",
                            createdAt = "",
                            head = HeadResult()
                        ),
                        PullRequestResponse(
                            title = "fakeTitle",
                            body = "bfakeBody",
                            createdAt = "",
                            head = HeadResult()
                        )
                    )
                }

                is PullRequestsType.EmptyListPullRequests ->
                    listOf()
            }
    }

    sealed class PullRequestsType {
        object EmptyListPullRequests : PullRequestsType()
        object Default : PullRequestsType()
    }
}
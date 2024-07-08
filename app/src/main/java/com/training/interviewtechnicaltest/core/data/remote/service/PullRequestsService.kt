package com.training.interviewtechnicaltest.core.data.remote.service

import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PullRequestsService {

    @GET("repos/{author}/{repo}/pulls")
    suspend fun getPullRequests(
        @Query("author") author: String,
        @Query("repo") repo: String,
    ): PullRequestResponse
}
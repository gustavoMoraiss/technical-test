package com.training.interviewtechnicaltest.core.data.remote.service

import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsService {

    @GET("repos/{author}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("author") author: String,
        @Path("repo") repo: String,
    ): List<PullRequestResponse?>
}
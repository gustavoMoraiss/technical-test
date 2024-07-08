package com.training.interviewtechnicaltest.core.data.remote.service

import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoriesService {

    @GET("search/repositories?q=language:Java&sort=stars&page={page}")
    suspend fun getRepositories(
        @Path("page") page: Int
    ): RepositoriesResponse
}
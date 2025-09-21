package com.training.interviewtechnicaltest.core.data.remote.service

import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getRepositories(
        @Query("page") page: Int
    ): RepositoriesResponse
}
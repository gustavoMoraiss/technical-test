package com.training.interviewtechnicaltest.core.data.remote.paging

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.core.data.remote.service.util.onEmpty
import com.training.interviewtechnicaltest.core.data.remote.service.util.onError
import com.training.interviewtechnicaltest.core.data.remote.service.util.onSuccess
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.repositories_feature.data.mapper.toRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource

class RepositoriesPageSource(
    private val remoteDataSource: RepositoriesRemoteDataSource,
    private val safeApiCaller: SafeApiCaller
) : PagingSource<Int, Repository>() {

    companion object {
        const val LIMITE_PAGES = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMITE_PAGES) ?: anchorPage?.nextKey?.minus(LIMITE_PAGES)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val pageNumber = params.key ?: 1
        safeApiCaller.safeApiCall {
            remoteDataSource.getRepositories(pageNumber)
        }.onSuccess { response ->
            return LoadResult.Page(
                data = response.repositories.toRepository(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (response.repositories.isEmpty()) null else pageNumber + 1
            )
        }.onEmpty {
            return LoadResult.Error(Throwable())
        }.onError {
            return LoadResult.Error(Throwable())
        }
        return LoadResult.Error(Throwable())
    }
}
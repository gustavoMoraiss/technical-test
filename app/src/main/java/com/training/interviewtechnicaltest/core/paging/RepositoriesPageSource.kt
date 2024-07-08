package com.training.interviewtechnicaltest.core.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.repositories_feature.data.mapper.toRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import okio.IOException

class RepositoriesPageSource(
    private val remoteDataSource: RepositoriesRemoteDataSource
) : PagingSource<Int, Repository>() {

    companion object{
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
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getRepositories(page = pageNumber)
            val repositories = response.repositories

            LoadResult.Page(
                data = repositories.toRepository(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (repositories.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }
}
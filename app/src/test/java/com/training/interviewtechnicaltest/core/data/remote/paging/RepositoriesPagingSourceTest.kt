package com.training.interviewtechnicaltest.core.data.remote.paging

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.training.interviewtechnicaltest.TestDispatcherRule
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.core.domain.model.RepositoriesFactory
import com.training.interviewtechnicaltest.repositories_feature.data.mapper.toRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoriesPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: RepositoriesRemoteDataSource

    @Mock
    lateinit var safeApiCaller: SafeApiCaller

    private val repositoriesPagingFactory =
        RepositoriesFactory.create(RepositoriesFactory.RepositoriesType.Default)


    private val repositoriesPagingSource by lazy {
        RepositoriesPageSource(remoteDataSource = remoteDataSource, safeApiCaller = safeApiCaller)
    }

    @Test
    suspend fun `must return success load result when load is called`() {

        //Given
        whenever(remoteDataSource.getRepositories(any())).thenReturn(repositoriesPagingFactory)

        //When
        val result = repositoriesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            RepositoriesFactory.create(RepositoriesFactory.RepositoriesType.Default).repositories.toRepository()
        )

        //Then
        assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(
            result
        )
    }


}
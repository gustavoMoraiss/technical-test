package com.training.interviewtechnicaltest.pullrequests

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.whenever
import com.training.interviewtechnicaltest.TestDispatcherRule
import com.training.interviewtechnicaltest.core.domain.model.RepositoriesFactory
import com.training.interviewtechnicaltest.repositories_feature.data.mapper.toRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.usecase.RepositoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.google.gson.JsonSyntaxException
import com.nhaarman.mockitokotlin2.any
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.PullRequestsFactory
import com.training.interviewtechnicaltest.pullrequests_feature.data.mapper.toPullRequestList
import com.training.interviewtechnicaltest.pullrequests_feature.domain.repository.PullRequestsRepository
import com.training.interviewtechnicaltest.pullrequests_feature.domain.usecase.PullRequestsUseCase
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.PullRequestsViewModel
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner.Silent::class)
class PullRequestsViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var pullRequestsUseCase: PullRequestsUseCase

    private val viewModel by lazy {
        PullRequestsViewModel(pullRequestsUseCase = pullRequestsUseCase)
    }

    private val fakePullRequests =
        PullRequestsFactory.create(PullRequestsFactory.PullRequestsType.Default)

    @Test
    fun `must validate paging data object values when calling paging data from pull requests`() =
        runTest {
            //Given
            whenever(pullRequestsUseCase.invoke(any(), any())).thenReturn(
                ResultData.Success(fakePullRequests.toPullRequestList())
            )

            //When
            val result = viewModel.uiState.value

            //Then
            assertThat(result).isNotNull()
        }

    @Test
    fun `must thrown an exception when the calling to the use case return an exception`() =
        runTest {
            //Given
            whenever(pullRequestsUseCase.invoke(any(), any())).thenThrow(RuntimeException())

            //When
            val result = viewModel.uiState.value

            //Then
            assertThat(result).isNotSameInstanceAs(ResultData.Success(fakePullRequests.toPullRequestList()))
        }
}
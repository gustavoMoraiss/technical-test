package com.training.interviewtechnicaltest.repositories_feature.presentation

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.whenever
import com.training.interviewtechnicaltest.TestDispatcherRule
import com.training.interviewtechnicaltest.repositories_feature.core.domain.model.RepositoriesFactory
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
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RepositoriesViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repositoriesUseCase: RepositoriesUseCase

    private val viewModel by lazy {
        RepositoriesViewModel(repositoriesUseCase = repositoriesUseCase)
    }

    private val fakePagingDataRepositories = PagingData.from(
        RepositoriesFactory.create(RepositoriesFactory.RepositoriesType.Default).repositories.toRepository()
    )

    @Test
    fun `must validate paging data object values when calling paging data from github repositories`() =
        runTest {
            //Given
            whenever(repositoriesUseCase.invoke()).thenReturn(
                flowOf(fakePagingDataRepositories)
            )

            //When
            val result = viewModel.uiState.repositories.first()

            //Then
            assertThat(result).isNotNull()
        }

    @Test
    fun `must thrown an exception when the calling to the use case  return an exception`() =
        runTest {
            //Given
            whenever(repositoriesUseCase.invoke()).thenThrow(RuntimeException())

            //When
            val result = viewModel.uiState.repositories

            //Then
            assertThat(result).isNotSameInstanceAs(fakePagingDataRepositories)
        }
}
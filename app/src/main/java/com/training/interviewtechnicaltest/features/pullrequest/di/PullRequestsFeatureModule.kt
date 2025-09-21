package com.training.interviewtechnicaltest.features.pullrequest.di

import com.training.interviewtechnicaltest.core.data.remote.service.PullRequestsService
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.features.pullrequest.data.repository.PullRequestsRepositoryImpl
import com.training.interviewtechnicaltest.features.pullrequest.data.source.PullRequestsRemoteDataSourceImpl
import com.training.interviewtechnicaltest.features.pullrequest.domain.repository.PullRequestsRepository
import com.training.interviewtechnicaltest.features.pullrequest.domain.source.PullRequestsRemoteDataSource
import com.training.interviewtechnicaltest.features.pullrequest.domain.usecase.PullRequestsUseCase
import com.training.interviewtechnicaltest.features.pullrequest.domain.usecase.PullRequestsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PullRequestsFeatureModule {

    @Provides
    @Singleton
    fun providePullRequestsRemoteDataSource(
        service: PullRequestsService,
        safeApiCaller: SafeApiCaller
    ): PullRequestsRemoteDataSource {
        return PullRequestsRemoteDataSourceImpl(service = service, safeApiCaller = safeApiCaller)
    }

    @Provides
    @Singleton
    fun providePullRequestsRepository(remoteDataSource: PullRequestsRemoteDataSource): PullRequestsRepository {
        return PullRequestsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun providePullRequestsUseCase(repository: PullRequestsRepository): PullRequestsUseCase {
        return PullRequestsUseCaseImpl(repository = repository)
    }
}
package com.training.interviewtechnicaltest.repositories_feature.di

import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.repositories_feature.data.repository.RepositoriesRepositoryImpl
import com.training.interviewtechnicaltest.repositories_feature.data.source.RepositoriesRemoteDataSourceImpl
import com.training.interviewtechnicaltest.repositories_feature.domain.repository.RepositoriesRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import com.training.interviewtechnicaltest.repositories_feature.domain.usecase.RepositoriesUseCase
import com.training.interviewtechnicaltest.repositories_feature.domain.usecase.RepositoriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesFeatureModule {

    @Provides
    @Singleton
    fun provideRepositoriesRemoteDataSource(service: RepositoriesService): RepositoriesRemoteDataSource {
        return RepositoriesRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideRepositoriesRepository(remoteDataSource: RepositoriesRemoteDataSource): RepositoriesRepository {
        return RepositoriesRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRepositoriesUseCase(repository: RepositoriesRepository): RepositoriesUseCase {
        return RepositoriesUseCaseImpl(repository = repository)
    }
}
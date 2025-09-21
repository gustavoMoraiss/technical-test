package com.training.interviewtechnicaltest.core.di

import com.training.interviewtechnicaltest.core.data.remote.service.PullRequestsService
import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.core.data.remote.service.util.ParamsInterceptor
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideSafeApiCaller(backgroundDispatcher: CoroutineDispatcher): SafeApiCaller {
        return SafeApiCaller(backgroundDispatcher)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Provides
    fun provideParamsInterceptor(): ParamsInterceptor {
        return ParamsInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor, loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(paramsInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRepositoriesService(
        client: OkHttpClient, converterFactory: GsonConverterFactory
    ): RepositoriesService {
        return Retrofit.Builder().baseUrl("https://api.github.com/").client(client)
            .addConverterFactory(converterFactory).build().create(RepositoriesService::class.java)
    }

    @Provides
    fun providePullRequestsService(
        client: OkHttpClient, converterFactory: GsonConverterFactory
    ): PullRequestsService {
        return Retrofit.Builder().baseUrl("https://api.github.com/").client(client)
            .addConverterFactory(converterFactory).build().create(PullRequestsService::class.java)
    }
}
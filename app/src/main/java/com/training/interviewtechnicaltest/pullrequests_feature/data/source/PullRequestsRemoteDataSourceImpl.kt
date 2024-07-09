package com.training.interviewtechnicaltest.pullrequests_feature.data.source

import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.data.remote.service.PullRequestsService
import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.core.data.remote.service.util.APIException
import com.training.interviewtechnicaltest.core.data.remote.service.util.ActionErrorTypeEnum
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.core.data.remote.service.util.onEmpty
import com.training.interviewtechnicaltest.core.data.remote.service.util.onError
import com.training.interviewtechnicaltest.core.data.remote.service.util.onSuccess
import com.training.interviewtechnicaltest.core.paging.RepositoriesPageSource
import com.training.interviewtechnicaltest.pullrequests_feature.domain.source.PullRequestsRemoteDataSource
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import javax.inject.Inject
import javax.xml.transform.Result

class PullRequestsRemoteDataSourceImpl @Inject constructor(
    private val service: PullRequestsService,
    private val safeApiCaller: SafeApiCaller
) : PullRequestsRemoteDataSource {

    override suspend fun getPullRequests(
        author: String,
        repo: String
    ): ResultData<PullRequestResponse> {
        var result: ResultData<PullRequestResponse> =
            ResultData.APIError(APIException(actionErrorType = ActionErrorTypeEnum.HTTP_ERROR))

        safeApiCaller.safeApiCall {
            service.getPullRequests(
                author = author,
                repo = repo
            )
        }.onSuccess { pullRequestResponse ->
            result = ResultData.Success(pullRequestResponse)
        }.onEmpty {
            result = ResultData.Empty()
        }.onError {
            result = ResultData.APIError(it.apiException)
        }

        return result
    }
}
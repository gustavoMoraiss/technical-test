package com.training.interviewtechnicaltest.features.pullrequest.data.source

import com.training.interviewtechnicaltest.core.data.remote.service.PullRequestsService
import com.training.interviewtechnicaltest.core.data.remote.service.util.APIException
import com.training.interviewtechnicaltest.core.data.remote.service.util.ActionErrorTypeEnum
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.core.data.remote.service.util.onEmpty
import com.training.interviewtechnicaltest.core.data.remote.service.util.onError
import com.training.interviewtechnicaltest.core.data.remote.service.util.onSuccess
import com.training.interviewtechnicaltest.core.domain.model.PullRequest
import com.training.interviewtechnicaltest.features.pullrequest.data.mapper.toPullRequestList
import com.training.interviewtechnicaltest.features.pullrequest.domain.source.PullRequestsRemoteDataSource
import javax.inject.Inject

class PullRequestsRemoteDataSourceImpl @Inject constructor(
    private val service: PullRequestsService,
    private val safeApiCaller: SafeApiCaller
) : PullRequestsRemoteDataSource {

    override suspend fun getPullRequests(
        author: String,
        repo: String
    ): ResultData<List<PullRequest?>> {
        var result: ResultData<List<PullRequest?>> =
            ResultData.APIError(APIException(actionErrorType = ActionErrorTypeEnum.HTTP_ERROR))

        safeApiCaller.safeApiCall {
            service.getPullRequests(
                author = author,
                repo = repo
            )
        }.onSuccess { pullRequestResponse ->
            result = ResultData.Success(pullRequestResponse.toPullRequestList())
        }.onEmpty {
            result = ResultData.Empty()
        }.onError {
            result = ResultData.APIError(it.apiException)
        }

        return result
    }
}
import com.google.gson.annotations.SerializedName
import com.training.interviewtechnicaltest.core.data.remote.model.OwnerResult
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse

data class HeadResult(
    @SerializedName("user")
    val user: OwnerResult,
    @SerializedName("repo")
    val repo: RepositoriesResponse
)
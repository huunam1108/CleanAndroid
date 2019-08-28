package namnh.clean.data.repository.source.remote

import io.reactivex.Single
import namnh.clean.data.repository.source.remote.api.GithubApi
import namnh.clean.data.repository.source.remote.api.response.RepoSearchResponse
import javax.inject.Inject

class RepoRemoteDataSource @Inject constructor(private val githubApi: GithubApi) {

    fun searchRepos(query: String, page: Int): Single<RepoSearchResponse> {
        return githubApi.searchRepos(query, page)
    }
}

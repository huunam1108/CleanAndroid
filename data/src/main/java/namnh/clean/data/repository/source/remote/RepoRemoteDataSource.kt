package namnh.clean.data.repository.source.remote

import io.reactivex.Single
import namnh.clean.data.repository.source.remote.api.GithubApi
import namnh.clean.data.repository.source.remote.api.response.RepoSearchResponse

class RepoRemoteDataSource(private val githubApi: GithubApi) {

    fun searchRepos(query: String, page: Int): Single<RepoSearchResponse> {
        return githubApi.searchRepos(query, page)
    }
}

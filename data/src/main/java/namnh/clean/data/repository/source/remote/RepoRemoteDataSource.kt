package namnh.clean.data.repository.source.remote

import namnh.clean.data.repository.source.remote.api.GithubApi
import namnh.clean.data.repository.source.remote.api.response.RepoSearchResponse

class RepoRemoteDataSource(private val githubApi: GithubApi) {

    suspend fun searchRepos(query: String, page: Int): RepoSearchResponse {
        return githubApi.searchRepos(query, page)
    }
}

package namnh.clean.data.repository.source.remote

import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.remote.api.GithubApi

class UserRemoteDataSource(private val githubApi: GithubApi) {
    suspend fun loadUser(login: String): UserData {
        return githubApi.getUser(login)
    }
}

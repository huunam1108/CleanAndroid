package namnh.clean.data.repository.source.remote

import io.reactivex.Single
import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.remote.api.GithubApi

class UserRemoteDataSource(private val githubApi: GithubApi) {
    fun loadUser(login: String): Single<UserData> {
        return githubApi.getUser(login)
    }
}

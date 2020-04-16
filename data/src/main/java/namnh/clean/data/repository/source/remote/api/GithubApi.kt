package namnh.clean.data.repository.source.remote.api

import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.remote.api.response.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): UserData

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String, @Query("page") page: Int): RepoSearchResponse
}

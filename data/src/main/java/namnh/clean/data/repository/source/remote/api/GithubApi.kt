package namnh.clean.data.repository.source.remote.api

import io.reactivex.Single
import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.remote.api.response.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<UserData>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Single<RepoSearchResponse>
}

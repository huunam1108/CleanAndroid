package namnh.clean.domain.repository

import io.reactivex.Single
import namnh.clean.domain.entity.Contributor
import namnh.clean.domain.entity.Repo

interface RepoRepository : Repository {
    fun loadRepos(owner: String): Single<List<Repo>>
    fun loadRepo(owner: String, name: String): Single<Repo>
    fun loadContributors(owner: String, name: String): Single<List<Contributor>>
    fun searchNextPage(query: String): Single<Boolean>
    fun search(query: String): Single<List<Repo>>
}

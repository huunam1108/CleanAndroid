package namnh.clean.domain.repository

import io.reactivex.Single
import namnh.clean.domain.entity.Repo

interface RepoRepository : Repository {
    fun search(query: String, page: Int): Single<List<Repo>>
}

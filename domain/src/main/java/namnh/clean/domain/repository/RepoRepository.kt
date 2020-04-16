package namnh.clean.domain.repository

import namnh.clean.domain.entity.Repo

interface RepoRepository {
    suspend fun search(query: String, page: Int): List<Repo>
}

package namnh.clean.data.repository

import io.reactivex.Single
import namnh.clean.data.model.collectionMap
import namnh.clean.data.repository.source.local.RepoLocalDataSource
import namnh.clean.data.repository.source.remote.RepoRemoteDataSource
import namnh.clean.domain.entity.Repo
import namnh.clean.domain.repository.RepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RepoRemoteDataSource,
    private val localDataSource: RepoLocalDataSource
) : RepoRepository {

    override fun search(query: String, page: Int): Single<List<Repo>> {
        return remoteDataSource.searchRepos(query, page).map { responses ->
            responses.items.collectionMap()
        }
    }
}

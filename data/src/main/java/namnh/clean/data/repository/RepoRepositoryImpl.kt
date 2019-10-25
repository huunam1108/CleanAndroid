package namnh.clean.data.repository

import io.reactivex.Single
import namnh.clean.data.model.DataMapper
import namnh.clean.data.repository.source.local.RepoLocalDataSource
import namnh.clean.data.repository.source.remote.RepoRemoteDataSource
import namnh.clean.domain.entity.Repo
import namnh.clean.domain.repository.RepoRepository

class RepoRepositoryImpl(
    private val remoteDataSource: RepoRemoteDataSource,
    private val localDataSource: RepoLocalDataSource,
    private val dataMapper: DataMapper
) : RepoRepository {

    override fun search(query: String, page: Int): Single<List<Repo>> {
        return remoteDataSource.searchRepos(query, page).map { responses ->
            responses.items.map {
                localDataSource.saveRepos(it)
            }
            responses.items
        }.map(dataMapper.collectionMap())
    }
}

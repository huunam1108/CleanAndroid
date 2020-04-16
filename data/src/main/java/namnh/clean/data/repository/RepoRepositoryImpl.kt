package namnh.clean.data.repository

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

    override suspend fun search(query: String, page: Int): List<Repo> {
        val response = remoteDataSource.searchRepos(query = query, page = page)
        localDataSource.saveRepos(response.items)
        return dataMapper.collectionMap(response.items)
    }
}

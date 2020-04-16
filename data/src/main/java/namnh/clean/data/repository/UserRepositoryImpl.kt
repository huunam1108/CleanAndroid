package namnh.clean.data.repository

import namnh.clean.data.model.DataMapper
import namnh.clean.data.repository.source.local.UserLocalDataSource
import namnh.clean.data.repository.source.remote.UserRemoteDataSource
import namnh.clean.domain.entity.User
import namnh.clean.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val dataMapper: DataMapper
) : UserRepository {

    override suspend fun loadUser(login: String): User {
        val response = remoteDataSource.loadUser(login)
        localDataSource.saveUser(response)
        return dataMapper.map(response)
    }
}

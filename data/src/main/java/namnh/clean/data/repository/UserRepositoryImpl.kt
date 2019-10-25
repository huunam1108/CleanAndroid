package namnh.clean.data.repository

import io.reactivex.Single
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

    override fun loadUser(login: String): Single<User> {
        return remoteDataSource.loadUser(login).doOnSuccess {
            localDataSource.saveUser(it)
        }.map(dataMapper.map())
    }
}

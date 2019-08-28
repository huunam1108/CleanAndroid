package namnh.clean.data.repository

import io.reactivex.Single
import namnh.clean.data.repository.source.local.UserLocalDataSource
import namnh.clean.data.repository.source.remote.UserRemoteDataSource
import namnh.clean.domain.entity.User
import namnh.clean.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override fun loadUser(login: String): Single<User> {
        return remoteDataSource.loadUser(login).map {
            it.map()
        }
    }
}

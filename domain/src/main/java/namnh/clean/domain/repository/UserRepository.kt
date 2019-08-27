package namnh.clean.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import namnh.clean.domain.entity.User

interface UserRepository : Repository {
    fun getUser(id: String, fromServer: Boolean): Single<User>
    fun signin(userName: String, password: String): Completable
    fun saveUser(user: User)
}

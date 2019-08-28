package namnh.clean.domain.repository

import io.reactivex.Single
import namnh.clean.domain.entity.User

interface UserRepository : Repository {
    fun loadUser(login: String): Single<User>
}

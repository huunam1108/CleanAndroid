package namnh.clean.domain.repository

import namnh.clean.domain.entity.User

interface UserRepository {
    suspend fun loadUser(login: String): User
}

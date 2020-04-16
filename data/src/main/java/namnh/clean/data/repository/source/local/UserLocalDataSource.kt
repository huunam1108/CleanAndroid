package namnh.clean.data.repository.source.local

import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.local.api.DatabaseApi
import namnh.clean.data.repository.source.local.api.SharedPrefApi

class UserLocalDataSource(
    private val githubDb: DatabaseApi,
    private val sharedPref: SharedPrefApi
) {
    suspend fun saveUser(user: UserData): Boolean {
        return githubDb.userDao().save(user) > 0
    }
}

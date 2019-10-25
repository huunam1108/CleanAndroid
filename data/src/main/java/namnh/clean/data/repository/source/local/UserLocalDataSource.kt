package namnh.clean.data.repository.source.local

import io.reactivex.Single
import namnh.clean.data.model.UserData
import namnh.clean.data.repository.source.local.api.DatabaseApi
import namnh.clean.data.repository.source.local.api.SharedPrefApi

class UserLocalDataSource(
    private val githubDb: DatabaseApi,
    private val sharedPref: SharedPrefApi
) {
    fun saveUser(user: UserData): Single<Boolean> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(githubDb.userDao().save(user) > 0)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}

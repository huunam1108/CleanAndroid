package namnh.clean.data.repository.source.local

import io.reactivex.Single
import namnh.clean.data.model.RepoData
import namnh.clean.data.repository.source.local.api.DatabaseApi
import namnh.clean.data.repository.source.local.api.SharedPrefApi

class RepoLocalDataSource(
    private val githubDb: DatabaseApi,
    private val sharedPref: SharedPrefApi
) {
    fun saveRepos(repo: RepoData): Single<Long> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(githubDb.repoDao().save(repo))
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}

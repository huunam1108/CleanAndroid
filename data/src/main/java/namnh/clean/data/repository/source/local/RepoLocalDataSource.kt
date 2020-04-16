package namnh.clean.data.repository.source.local

import namnh.clean.data.model.RepoData
import namnh.clean.data.repository.source.local.api.DatabaseApi
import namnh.clean.data.repository.source.local.api.SharedPrefApi

class RepoLocalDataSource(
    private val githubDb: DatabaseApi,
    private val sharedPref: SharedPrefApi
) {
    suspend fun saveRepos(repos: List<RepoData>) {
        return githubDb.repoDao().save(repos)
    }
}

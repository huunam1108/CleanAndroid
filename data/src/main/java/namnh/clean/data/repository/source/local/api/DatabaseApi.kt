package namnh.clean.data.repository.source.local.api

import namnh.clean.data.repository.source.local.api.db.dao.RepoDao
import namnh.clean.data.repository.source.local.api.db.dao.UserDao

interface DatabaseApi {
    fun userDao() : UserDao
    fun repoDao() : RepoDao
}

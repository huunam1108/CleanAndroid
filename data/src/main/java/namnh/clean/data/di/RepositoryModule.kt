package namnh.clean.data.di

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import namnh.clean.data.model.DataMapper
import namnh.clean.data.repository.RepoRepositoryImpl
import namnh.clean.data.repository.UserRepositoryImpl
import namnh.clean.data.repository.source.local.RepoLocalDataSource
import namnh.clean.data.repository.source.local.UserLocalDataSource
import namnh.clean.data.repository.source.local.api.DatabaseApi
import namnh.clean.data.repository.source.local.api.SharedPrefApi
import namnh.clean.data.repository.source.local.api.db.DatabaseApiImpl
import namnh.clean.data.repository.source.local.api.db.DatabaseManager
import namnh.clean.data.repository.source.local.api.db.MigrationManager
import namnh.clean.data.repository.source.local.api.pref.SharedPrefApiImpl
import namnh.clean.data.repository.source.remote.RepoRemoteDataSource
import namnh.clean.data.repository.source.remote.UserRemoteDataSource
import namnh.clean.domain.repository.RepoRepository
import namnh.clean.domain.repository.UserRepository
import namnh.clean.shared.DatabaseConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private fun appDatabase(context: Context): DatabaseManager = Room.databaseBuilder(
    context,
    DatabaseManager::class.java,
    DatabaseConfig.DATABASE_NAME
).addMigrations(
    MigrationManager.MIGRATION_1_2
).build()

private fun contentResolver(context: Context): ContentResolver = context.contentResolver

val repositoryModule = module {

    //androidContext()
    single<SharedPrefApi> { SharedPrefApiImpl(/*context*/get(), /*gson*/get()) }
    single<DatabaseApi> { DatabaseApiImpl(/*DatabaseManager*/get()) }

    single { UserLocalDataSource(/*DatabaseApi*/get(), /*SharedPrefApi*/get()) }
    single { UserRemoteDataSource(/*GithubApi*/get()) }
    single<UserRepository> {
        UserRepositoryImpl(
            /*UserRemoteDataSource*/get(),
            /*UserLocalDataSource*/get(),
            /*DataMapper*/get()
        )
    }

    single { /*DataMapper*/ DataMapper() }

    single { RepoLocalDataSource(/*DatabaseApi*/get(), /*SharedPrefApi*/get()) }
    single { RepoRemoteDataSource(/*GithubApi*/get()) }
    single<RepoRepository> {
        RepoRepositoryImpl(
            /*RepoRemoteDataSource*/get(),
            /*RepoLocalDataSource*/get(),
            /*DataMapper*/get()
        )
    }

    single { contentResolver(/*ApplicationContext*/get()) }

    single {
        /*DatabaseManager*/
        appDatabase(/*ApplicationContext*/get())
    }
}

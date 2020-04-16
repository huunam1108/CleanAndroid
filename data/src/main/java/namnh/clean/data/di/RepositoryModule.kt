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
import namnh.clean.data.repository.source.config.DatabaseConfig
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
    single<SharedPrefApi> { SharedPrefApiImpl(get(), get()) }
    single<DatabaseApi> { DatabaseApiImpl(get()) }
    single { UserLocalDataSource(get(), get()) }
    single { UserRemoteDataSource(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single { DataMapper() }
    single { RepoLocalDataSource(get(), get()) }
    single { RepoRemoteDataSource(get()) }
    single<RepoRepository> { RepoRepositoryImpl(get(), get(), get()) }
    single { contentResolver(get()) }
    single { appDatabase(get()) }
}

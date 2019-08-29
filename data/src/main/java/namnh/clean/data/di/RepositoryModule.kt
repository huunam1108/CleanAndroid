package namnh.clean.data.di

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
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
import javax.inject.Singleton

@Suppress("unused")
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context, gson: Gson): SharedPrefApi {
        return SharedPrefApiImpl(context, gson)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: UserLocalDataSource,
        dataMapper: DataMapper
    ): UserRepository {
        return UserRepositoryImpl(remoteDataSource, localDataSource, dataMapper)
    }

    @Singleton
    @Provides
    fun provideRepoRepository(
        remoteDataSource: RepoRemoteDataSource,
        localDataSource: RepoLocalDataSource,
        dataMapper: DataMapper
    ): RepoRepository {
        return RepoRepositoryImpl(remoteDataSource, localDataSource, dataMapper)
    }

    @Singleton
    @Provides
    fun provideContentResolver(context: Context): ContentResolver {
        return context.contentResolver
    }

    @Singleton
    @Provides
    fun provideDatabaseManager(context: Context): DatabaseManager {
        return Room.databaseBuilder(
            context,
            DatabaseManager::class.java,
            DatabaseConfig.DATABASE_NAME
        ).addMigrations(
            MigrationManager.MIGRATION_1_2
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabaseApi(databaseManager: DatabaseManager): DatabaseApi {
        return DatabaseApiImpl(databaseManager)
    }
}

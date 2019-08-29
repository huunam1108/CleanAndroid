package namnh.clean.github.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import namnh.clean.data.executor.JobExecutor
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.github.MainThread
import javax.inject.Singleton

@Suppress("unused")
@Module(includes = [ViewModelModule::class])
// this class must be not an abstraction
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Singleton
    @Provides
    fun providePostExecutionThread(mainThread: MainThread): PostExecutionThread {
        return mainThread
    }
}

package namnh.clean.github

import android.app.Application
import namnh.clean.data.di.networkModule
import namnh.clean.data.di.repositoryModule
import namnh.clean.github.di.appModule
import namnh.clean.github.di.presentationMapperModule
import namnh.clean.github.di.useCaseModule
import namnh.clean.github.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules =
            listOf(
                appModule,
                repositoryModule,
                networkModule,
                presentationMapperModule,
                useCaseModule,
                viewModelModule
            )
        startKoin {
            androidLogger()
            androidContext(this@GithubApp)
            modules(modules)
        }
    }
}

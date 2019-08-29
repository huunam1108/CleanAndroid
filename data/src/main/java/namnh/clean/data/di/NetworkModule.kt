package namnh.clean.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import namnh.clean.data.repository.source.remote.api.GithubApi
import namnh.clean.data.repository.source.remote.api.ServiceGenerator
import namnh.clean.shared.ApiConfig
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        // FIXME: try to check build type (production, dev, staging...)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideGithubApi(
        gson: Gson,
        loggingInterceptor: HttpLoggingInterceptor
    ): GithubApi {
        return ServiceGenerator.generate(
            ApiConfig.BASE_URL,
            GithubApi::class.java,
            gson,
            loggingInterceptor
        )
    }
}

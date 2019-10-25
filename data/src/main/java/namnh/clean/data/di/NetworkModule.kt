package namnh.clean.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import namnh.clean.data.BuildConfig
import namnh.clean.data.repository.source.remote.api.GithubApi
import namnh.clean.data.repository.source.remote.api.ServiceGenerator
import namnh.clean.shared.ApiConfig
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

private fun buildGson(): Gson {
    return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
}

private fun buildHttpLog(): HttpLoggingInterceptor {
    val logLevel =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return HttpLoggingInterceptor().setLevel(logLevel)
}

val networkModule = module {
    single { /*Gson*/ buildGson() }
    single {
        /*GithubApi*/
        ServiceGenerator.generate(
            ApiConfig.BASE_URL,
            GithubApi::class.java,
            get(),
            buildHttpLog()
        )
    }
    // Other APIs
}

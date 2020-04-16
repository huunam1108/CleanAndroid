package namnh.clean.data.repository.source.remote.api

import com.google.gson.Gson
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private const val CONNECT_TIMEOUT = 60000L
    private const val READ_TIMEOUT = 20000L
    private const val WRITE_TIMEOUT = 30000L

    fun <T> generate(
        baseUrl: String,
        serviceClass: Class<T>,
        gson: Gson,
        vararg interceptors: Interceptor
    ): T {
        val okHttpBuilder = OkHttpClient().newBuilder()
        for (itr in interceptors) {
            okHttpBuilder.addInterceptor(itr)
        }
        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()
        return retrofit.create(serviceClass)
    }
}

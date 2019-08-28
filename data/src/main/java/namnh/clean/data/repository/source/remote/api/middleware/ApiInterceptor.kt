package namnh.clean.data.repository.source.remote.api.middleware

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

open class ApiInterceptor @Inject constructor(private val userAgent: UserAgent) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(buildRequest(chain))
    }

    open fun buildRequest(chain: Interceptor.Chain): Request {
        return chain.request()
    }

    internal fun buildUserAgent(): String {
        return userAgent.getDeviceType() + " " + userAgent.getAppVersion() + " " + userAgent.getAppId()
    }
}

package namnh.clean.data.repository.source.remote.api.middleware

const val USER_AGENT = "X-User-Agent"

interface UserAgent {

    fun getAppVersion(): String

    fun getAppId(): String

    fun getDeviceType(): String
}

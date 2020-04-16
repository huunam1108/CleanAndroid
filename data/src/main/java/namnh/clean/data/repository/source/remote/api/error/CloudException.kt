package namnh.clean.data.repository.source.remote.api.error

import namnh.clean.data.repository.source.remote.api.response.BaseErrorResponse
import retrofit2.Response

class CloudException(
    private val kind: Kind,
    private val serverErrorResponse: BaseErrorResponse? = null,
    private val response: Response<*>? = null,
    private val exception: Throwable? = null
) : RuntimeException() {

    companion object {
        fun httpError(
            response: Response<*>,
            error: BaseErrorResponse? = null
        ): CloudException {
            return CloudException(
                Kind.HTTP,
                error,
                response
            )
        }

        fun serverError(serverErrorResponse: BaseErrorResponse): CloudException {
            return CloudException(
                Kind.SERVER,
                serverErrorResponse
            )
        }

        fun networkError(throwable: Throwable): CloudException {
            return CloudException(
                Kind.NETWORK,
                null,
                null,
                throwable
            )
        }

        fun unexpectedError(throwable: Throwable): CloudException {
            return CloudException(
                Kind.UNEXPECTED,
                null,
                null,
                throwable
            )
        }
    }

    /**
     * Return converted [BaseErrorResponse] from server side if exists.
     * Available in [Kind.SERVER] && [Kind.HTTP].
     */
    fun errorResponse(): BaseErrorResponse? {
        return serverErrorResponse
    }

    /**
     * Return raw error response from server side if exists.
     * Available in [Kind.HTTP].
     */
    fun rawErrorResponse(): Response<*>? {
        return response
    }

    /**
     * Return unexpected error when proceed api call. <br>
     * Available in [Kind.NETWORK] && [Kind.UNEXPECTED].
     */
    fun unexpectedError(): Throwable? {
        return exception
    }

    fun isNetworkError(): Boolean {
        return kind == Kind.NETWORK
    }

    fun isServerError(): Boolean {
        return kind == Kind.SERVER
    }

    fun isHttpError(): Boolean {
        return kind == Kind.HTTP
    }

    fun isUnexpectedError(): Boolean {
        return kind == Kind.UNEXPECTED
    }

    enum class Kind {
        /** An [IOException] or [UnknownHostException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}

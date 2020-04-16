package namnh.clean.data.repository.source.remote.api.error

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.UnknownHostException
import namnh.clean.data.repository.source.remote.api.response.BaseErrorResponse
import namnh.clean.domain.error.ErrorMapper
import retrofit2.HttpException

class CloudExceptionMapper : ErrorMapper<CloudException> {
    override fun map(throwable: Throwable): CloudException {
        if (throwable is HttpException) {
            val response = throwable.response()
            if (response?.errorBody() != null) {
                val errorResponse = response.errorBody()!!.string()
                val serverErrorResponse = deserializeServerError(errorResponse)
                return if (serverErrorResponse != null) {
                    CloudException.serverError(serverErrorResponse)
                } else CloudException.httpError(response)
            }
        }

        if (throwable is IOException || throwable is UnknownHostException) {
            return CloudException.networkError(throwable)
        }
        return CloudException.unexpectedError(throwable)
    }

    private fun deserializeServerError(errorString: String): BaseErrorResponse? {
        val gson = GsonBuilder().create()
        return try {
            gson.fromJson(errorString, BaseErrorResponse::class.java)
        } catch (e: JsonSyntaxException) {
            null
        }
    }
}

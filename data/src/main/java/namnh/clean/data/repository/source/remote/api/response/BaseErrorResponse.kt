package namnh.clean.data.repository.source.remote.api.response

import com.google.gson.annotations.Expose

class BaseErrorResponse(
    @Expose
    val success: Boolean,
    @Expose
    val errors: List<Error>
) {
    class Error(
        @Expose
        val code: Int,
        @Expose
        val message: String,
        @Expose
        val resource: String,
        @Expose
        val field: String?
    )
}

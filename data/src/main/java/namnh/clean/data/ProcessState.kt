package namnh.clean.data

data class ProcessState<out T>(
    private val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null
) {
    val isSuccess = this.status is Success
    val isError = this.status is Error
    val isLoading = this.status is Loading

    companion object {
        fun <T> loading(): ProcessState<T> = ProcessState(status = Loading)
        fun <T> success(data: T? = null): ProcessState<T> {
            return ProcessState(status = Success, data = data)
        }

        fun <T> error(throwable: Throwable?): ProcessState<T> {
            return ProcessState(status = Error, throwable = throwable)
        }
    }
}

sealed class Status
object Loading : Status()
object Success : Status()
object Error : Status()

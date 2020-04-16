package namnh.clean.domain.error

interface ErrorMapper<T> {
    fun map(throwable: Throwable): T
}

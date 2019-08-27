package namnh.clean.domain.entity

data class User(
    val login: String,
    val avatarUrl: String?,
    val name: String?,
    val company: String?,
    val reposUrl: String?,
    val blog: String?
) : BaseEntity()

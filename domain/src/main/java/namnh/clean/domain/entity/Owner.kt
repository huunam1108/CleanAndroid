package namnh.clean.domain.entity

data class Owner(
    val login: String,
    val url: String?
) : BaseEntity()

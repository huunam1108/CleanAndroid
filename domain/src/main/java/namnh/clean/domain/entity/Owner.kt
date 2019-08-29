package namnh.clean.domain.entity

data class Owner(
    val id: Long,
    val login: String?,
    val url: String?
) : BaseEntity()

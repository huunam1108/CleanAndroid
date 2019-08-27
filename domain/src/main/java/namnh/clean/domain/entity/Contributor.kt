package namnh.clean.domain.entity

data class Contributor(
    val login: String,
    val contributors: Int,
    val avatarUrl: String?
) : BaseEntity()

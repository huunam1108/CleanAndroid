package namnh.clean.domain.entity

data class Repo(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: Owner,
    val stars: Int
) : BaseEntity() {
    data class Owner(
        val login: String,
        val url: String?
    )
}

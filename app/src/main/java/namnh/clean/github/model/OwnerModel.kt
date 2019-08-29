package namnh.clean.github.model

data class OwnerModel(
    val id: Long,
    val login: String?,
    val url: String?
) : PresentationModel()

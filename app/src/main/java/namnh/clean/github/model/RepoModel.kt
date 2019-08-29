package namnh.clean.github.model

data class RepoModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: OwnerModel,
    val stars: Int
) : PresentationModel()


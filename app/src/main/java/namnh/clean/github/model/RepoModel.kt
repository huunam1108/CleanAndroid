package namnh.clean.github.model

import namnh.clean.shared.adapter.RecyclerViewItem
import namnh.clean.shared.adapter.TYPE_REPO

data class RepoModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: OwnerModel,
    val stars: Int
) : PresentationModel(), RecyclerViewItem {
    override val type: Int
        get() = TYPE_REPO
}

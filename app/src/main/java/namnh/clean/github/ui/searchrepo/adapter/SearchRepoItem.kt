package namnh.clean.github.ui.searchrepo.adapter

import namnh.clean.github.model.RepoModel
import namnh.clean.shared.adapter.RecyclerViewItem
import namnh.clean.shared.adapter.TYPE_ITEM

data class SearchRepoItem(val repoModel: RepoModel, override var type: Int = TYPE_ITEM) :
    RecyclerViewItem

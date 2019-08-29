package namnh.clean.github.ui.searchrepo.adapter

import android.view.ViewGroup
import namnh.clean.github.R
import namnh.clean.shared.adapter.BaseAdapter
import namnh.clean.shared.adapter.BaseDiffUtil

class SearchRepoAdapter(private val onItemClickListener: (SearchRepoItem?) -> Unit) :
    BaseAdapter(DIFF_CALLBACK) {
    override fun customViewHolder(parent: ViewGroup, viewType: Int): Any {
        return SearchRepoViewHolder(
            inflateView(R.layout.item_search_repo, parent),
            onItemClickListener
        )
    }

    companion object {
        private val DIFF_CALLBACK = object : BaseDiffUtil<SearchRepoItem>() {
            override fun areItemsTheSame(
                oldItem: SearchRepoItem,
                newItem: SearchRepoItem
            ): Boolean {
                return oldItem.repoModel.id == newItem.repoModel.id
            }
        }
    }
}

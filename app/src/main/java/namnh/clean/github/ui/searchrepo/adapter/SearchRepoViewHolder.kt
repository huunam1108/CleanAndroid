package namnh.clean.github.ui.searchrepo.adapter

import android.view.View
import kotlinx.android.synthetic.main.item_search_repo.view.*
import namnh.clean.shared.adapter.BaseViewHolder

class SearchRepoViewHolder(itemView: View, itemClickListener: (SearchRepoItem?) -> Unit) :
    BaseViewHolder<SearchRepoItem>(itemView) {

    init {
        itemView.apply {
            setOnClickListener {
                itemClickListener(item)
            }
        }
    }

    override fun bind(item: SearchRepoItem) {
        super.bind(item)
        itemView.apply {
            tv_star.text = item.repoModel.stars.toString()
            tv_fullname.text = item.repoModel.fullName
            tv_owner.text = item.repoModel.owner.login
        }
    }
}
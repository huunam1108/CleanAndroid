package namnh.clean.github.ui.searchrepo

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search_repo.*
import namnh.clean.github.R
import namnh.clean.github.model.state.Error
import namnh.clean.github.model.state.Finish
import namnh.clean.github.model.state.Loading
import namnh.clean.github.model.state.Success
import namnh.clean.github.ui.searchrepo.adapter.SearchRepoAdapter
import namnh.clean.shared.adapter.loadmore.LoadMoreAdapter
import namnh.clean.shared.adapter.loadmore.LoadMoreController
import namnh.clean.shared.adapter.loadmore.LoadMoreWrapper
import namnh.clean.shared.util.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRepoFragment : Fragment() {

    private val searchRepoViewModel: SearchRepoViewModel by viewModel()
    private var repoAdapter by autoCleared<SearchRepoAdapter>()
    private var loadMoreController: LoadMoreController? = null
    private var currentPage: Int = INITIAL_PAGE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_repo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        observers()
        initSearchInputListener()
    }

    private fun initViews() {
        repoAdapter = SearchRepoAdapter { repo ->
            Toast.makeText(context, "${repo?.repoModel?.name}", Toast.LENGTH_LONG).show()
        }
        rv_repos.layoutManager = LinearLayoutManager(context)
        LoadMoreWrapper.with(this.repoAdapter)
            .setLoadMoreListener(object : LoadMoreAdapter.OnLoadMoreListener {
                override fun onPrepared(loadMoreController: LoadMoreController?) {
                    this@SearchRepoFragment.loadMoreController = loadMoreController
                }

                override fun onLoadMore() {
                    // FIXME: This just for sample app, don't do this
                    currentPage += 1
                    searchRepoViewModel.reqNextPage(currentPage)
                }
            }).into(rv_repos)
    }

    private fun observers() {
        searchRepoViewModel.results.observe(viewLifecycleOwner, Observer { state ->
            when (state.status) {
                is Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is Finish -> {
                    progress.visibility = View.GONE
                }
                is Success -> {
                    state.data?.let {
                        repoAdapter.submitList(it)
                    }
                    // FIXME: This just for sample app, don't do this
                    if (currentPage == 5) {
                        loadMoreController?.setReachEnd(true)
                    }
                }
                is Error -> {
                    Toast.makeText(context, "Can not get data!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initSearchInputListener() {
        edt_search.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(textView)
                true
            } else
                false
        }
    }

    private fun doSearch(textView: TextView) {
        dismissKeyboard(textView.windowToken)
        searchRepoViewModel.setQuery(textView.text.toString())
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {
        const val INITIAL_PAGE = 1
        fun newInstance() = SearchRepoFragment()
    }
}

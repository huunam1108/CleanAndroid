package namnh.clean.github.ui.searchrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import namnh.clean.domain.entity.Repo
import namnh.clean.domain.interactor.outputport.SingleObserver
import namnh.clean.domain.interactor.usecase.SearchReposUseCase
import namnh.clean.github.model.mapper.RepoModelMapper
import namnh.clean.github.model.state.ProcessState
import namnh.clean.github.ui.searchrepo.adapter.SearchRepoItem
import namnh.clean.shared.adapter.RecyclerViewItem
import namnh.clean.shared.util.AbsentLiveData
import java.util.*
import javax.inject.Inject

class SearchRepoViewModel @Inject constructor(
    private val searchReposUseCase: SearchReposUseCase,
    private val repoModelMapper: RepoModelMapper
) : ViewModel() {

    private val query = MutableLiveData<String>()
    private val nextPage = MutableLiveData<Int?>().apply { value = 1 }
    private var searchData = mutableListOf<RecyclerViewItem>()
    val results: LiveData<ProcessState<List<RecyclerViewItem>>> = Transformations
        .switchMap(query) { search ->
            if (search.isNullOrBlank()) {
                AbsentLiveData.create()
            } else {
                searchRepo(search, nextPage.value ?: 1)
            }
        }

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == query.value) return
        query.value = input
    }

    fun reqNextPage(next: Int) {
        if (nextPage.value == next) return
        nextPage.value = next
        val temp = query.value
        query.value = ""
        query.value = temp
    }

    private fun searchRepo(
        query: String,
        page: Int = 1
    ): LiveData<ProcessState<List<RecyclerViewItem>>> {
        val liveData = MutableLiveData<ProcessState<List<RecyclerViewItem>>>()
        searchReposUseCase.execute(
            SearchReposUseCase.Input(query, page),
            object : SingleObserver<List<Repo>>() {
                override fun onSubscribe() {
                    if (page == 1) {
                        liveData.value = ProcessState.loading()
                    }
                }

                override fun onSuccess(data: List<Repo>) {
                    if (page == 1) {
                        searchData = searchData.dropLast(searchData.size).toMutableList()
                    }
                    searchData.addAll(repoModelMapper.collectionMap(data).map { model ->
                        SearchRepoItem(model)
                    })
                    liveData.value = ProcessState.success(searchData.toList())
                }

                override fun onError(throwable: Throwable) {
                    liveData.value = ProcessState.error(throwable)
                }

                override fun doFinally() {
                    liveData.value = ProcessState.finish()
                }
            })
        return liveData
    }
}

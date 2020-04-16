package namnh.clean.github.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import namnh.clean.data.ProcessState
import namnh.clean.domain.scheduler.DispatchersProvider

abstract class BaseViewModel : ViewModel() {
    val exception = MediatorLiveData<Throwable>()
    val loading = MediatorLiveData<Boolean>()

    init {
        loading.addSource(exception) { loading.value = it == null }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        exception.postValue(throwable)
    }

    protected val scope = viewModelScope.plus(exceptionHandler)

    protected fun <T> addSource(vararg liveDatas: LiveData<out ProcessState<T>>) {
        liveDatas.map { liveData ->
            loading.addSource(liveData) { state ->
                loading.value = state.isLoading
            }
        }
    }

    fun launchDataLoad(dispatchers: DispatchersProvider, block: suspend () -> Unit): Job {
        return scope.launch {
            try {
                loading.value = true
                withContext(dispatchers.dispatcher()) {
                    block()
                }
            } catch (e: Throwable) {
                exception.value = e
            } finally {
                loading.value = false
            }
        }
    }
}

package namnh.clean.domain.interactor.inputport

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor

abstract class UseCase<in Input, out Output>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    internal fun add(disposable: Disposable) {
        if (!disposable.isDisposed) {
            compositeDisposable.add(disposable)
        }
    }

    internal fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    internal fun threadExecutor(): Scheduler {
        return Schedulers.from(threadExecutor)
    }

    internal fun postExecutionThread(): Scheduler {
        return postExecutionThread.getScheduler()
    }

    open fun onCleared() {}
    abstract fun buildUseCase(input: Input): Output
}

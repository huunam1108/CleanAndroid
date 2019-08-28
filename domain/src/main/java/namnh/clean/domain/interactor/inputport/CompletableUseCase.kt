package namnh.clean.domain.interactor.inputport

import io.reactivex.Completable
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.domain.interactor.outputport.CompletableObserver

abstract class CompletableUseCase<in Input>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<Input, Completable>(threadExecutor, postExecutionThread) {

    fun execute(input: Input, observer: CompletableObserver) {
        val observable = buildUseCase(input)
            .subscribeOn(threadExecutor())
            .observeOn(postExecutionThread())
            .doOnSubscribe(observer.subscribeConsumer())
            .doFinally(observer.finally())
        add(
            observable.subscribe(
                observer.completeConsumer(),
                observer.errorConsumer()
            )
        )
    }
}

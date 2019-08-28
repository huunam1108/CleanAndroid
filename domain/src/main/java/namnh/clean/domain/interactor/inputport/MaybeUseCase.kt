package namnh.clean.domain.interactor.inputport

import io.reactivex.Maybe
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.domain.interactor.outputport.MaybeObserver

abstract class MaybeUseCase<in Input, T>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<Input, Maybe<T>>(threadExecutor, postExecutionThread) {

    fun execute(input: Input, observer: MaybeObserver<T>) {
        val observable = buildUseCase(input)
            .subscribeOn(threadExecutor())
            .observeOn(postExecutionThread())
            .doOnSubscribe(observer.subscribeConsumer())
            .doFinally(observer.finally())
        add(
            observable.subscribe(
                observer.successConsumer(),
                observer.errorConsumer(),
                observer.completeConsumer()
            )
        )
    }
}

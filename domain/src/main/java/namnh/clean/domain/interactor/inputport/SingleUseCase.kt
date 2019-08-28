package namnh.clean.domain.interactor.inputport

import io.reactivex.Single
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.domain.interactor.outputport.SingleObserver

abstract class SingleUseCase<in Input, T>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<Input, Single<T>>(threadExecutor, postExecutionThread) {

    fun execute(input: Input, observer: SingleObserver<T>) {
        val observable = buildUseCase(input)
            .subscribeOn(threadExecutor())
            .observeOn(postExecutionThread())
            .doOnSubscribe(observer.subscribeConsumer())
            .doFinally(observer.finally())
        add(
            observable.subscribe(
                observer.successConsumer(),
                observer.errorConsumer()
            )
        )
    }
}

package namnh.clean.domain.interactor.outputport

import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

abstract class DefaultObserver {

    fun subscribeConsumer(): Consumer<Any> {
        return Consumer { onSubscribe() }
    }

    fun errorConsumer(): Consumer<in Throwable> {
        return Consumer { onError(it) }
    }

    fun finally(): Action {
        return Action { doFinally() }
    }

    open fun onSubscribe() {}
    open fun onError(throwable: Throwable) {}
    open fun doFinally() {}
}
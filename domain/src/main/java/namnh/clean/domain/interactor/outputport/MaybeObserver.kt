package namnh.clean.domain.interactor.outputport

import io.reactivex.annotations.NonNull
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

abstract class MaybeObserver<T> : DefaultObserver() {

    fun successConsumer(): Consumer<T> {
        return Consumer { onSuccess(it) }
    }

    fun completeConsumer(): Action {
        return Action { onComplete() }
    }

    abstract fun onComplete()
    abstract fun onSuccess(@NonNull data: T)
}

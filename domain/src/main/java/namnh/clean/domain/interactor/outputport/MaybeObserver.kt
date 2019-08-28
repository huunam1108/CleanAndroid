package namnh.clean.domain.interactor.outputport

import io.reactivex.annotations.NonNull
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class MaybeObserver<T> : DefaultObserver() {

    fun successConsumer(): Consumer<T> {
        return Consumer { onSuccess(it) }
    }

    fun completeConsumer(): Action {
        return Action { onComplete() }
    }

    open fun onComplete() {}
    open fun onSuccess(@NonNull data: T) {}
}

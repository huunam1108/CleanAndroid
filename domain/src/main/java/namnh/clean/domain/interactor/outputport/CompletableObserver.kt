package namnh.clean.domain.interactor.outputport

import io.reactivex.functions.Action

abstract class CompletableObserver : DefaultObserver() {

    fun completeConsumer(): Action {
        return Action { onComplete() }
    }

    abstract fun onComplete()
}

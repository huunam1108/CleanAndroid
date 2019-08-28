package namnh.clean.domain.interactor.outputport

import io.reactivex.functions.Action

class CompletableObserver : DefaultObserver() {

    fun completeConsumer(): Action {
        return Action { onComplete() }
    }

    open fun onComplete() {}
}

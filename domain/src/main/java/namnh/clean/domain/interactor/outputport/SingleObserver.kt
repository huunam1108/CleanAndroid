package namnh.clean.domain.interactor.outputport

import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer

class SingleObserver<T> : DefaultObserver() {

    fun successConsumer(): Consumer<T> {
        return Consumer { onSuccess(it) }
    }

    open fun onSuccess(@NonNull data: T) {}
}

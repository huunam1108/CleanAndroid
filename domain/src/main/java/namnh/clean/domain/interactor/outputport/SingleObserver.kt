package namnh.clean.domain.interactor.outputport

import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer

abstract class SingleObserver<T> : DefaultObserver() {

    fun successConsumer(): Consumer<T> {
        return Consumer { onSuccess(it) }
    }

    abstract fun onSuccess(@NonNull data: T)
}

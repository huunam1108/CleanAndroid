package namnh.clean.domain.usecase.base

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import namnh.clean.domain.interactor.inputport.BaseInput
import namnh.clean.domain.interactor.outputport.BaseObserver
import namnh.clean.domain.scheduler.ControlledRunner
import namnh.clean.domain.scheduler.DispatchersProvider
import namnh.clean.domain.scheduler.IO
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

/**
 * For flow UseCase.
 */
abstract class BaseFlowUseCase<in Input : BaseInput, Output> : KoinComponent {
    open val dispatchersProvider = get<DispatchersProvider>(named(IO))

    protected abstract suspend fun buildUseCase(input: Input): Flow<Output>

    private var controlledRunner = ControlledRunner<Unit>()

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(input: Input, block: BaseObserver<out Output>.() -> Unit) {
        controlledRunner.cancelPreviousThenRun {
            val response = BaseObserver<Output>().apply { block() }
            response()
            try {
                withContext(dispatchersProvider.dispatcher()) {
                    buildUseCase(input).handleErrors {
                        response(it)
                    }.collect {
                        withContext(Dispatchers.Main) {
                            response(it)
                        }
                    }
                }
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                response(e)
            }
        }
    }
}

@ExperimentalCoroutinesApi
fun <T> Flow<T>.handleErrors(error: (Throwable) -> Unit): Flow<T> = catch { e -> error(e) }

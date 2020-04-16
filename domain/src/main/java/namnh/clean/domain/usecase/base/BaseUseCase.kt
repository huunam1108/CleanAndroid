package namnh.clean.domain.usecase.base

import kotlinx.coroutines.CancellationException
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
 * For Oneshot UseCase
 */
abstract class BaseUseCase<in Input : BaseInput, out Output> : KoinComponent {
    open val dispatchersProvider = get<DispatchersProvider>(named(IO))

    protected abstract suspend fun buildUseCase(input: Input): Output

    private var controlledRunner = ControlledRunner<Unit>()

    suspend operator fun invoke(input: Input, block: BaseObserver<out Output>.() -> Unit) {
        controlledRunner.cancelPreviousThenRun {
            val response = BaseObserver<Output>().apply { block() }
            response()
            try {
                val result = withContext(dispatchersProvider.dispatcher()) {
                    buildUseCase(input)
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                response(e)
            }
        }
    }
}

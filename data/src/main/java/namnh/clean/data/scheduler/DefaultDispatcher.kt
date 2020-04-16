package namnh.clean.data.scheduler

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import namnh.clean.domain.scheduler.DispatchersProvider

class DefaultDispatcher : DispatchersProvider {
    override fun dispatcher(): CoroutineContext {
        return Dispatchers.Default
    }
}

package namnh.clean.domain.scheduler

import kotlin.coroutines.CoroutineContext

interface DispatchersProvider {
    fun dispatcher(): CoroutineContext
}

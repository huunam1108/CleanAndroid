package namnh.clean.github

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import namnh.clean.domain.scheduler.DispatchersProvider

class MainDispatcher : DispatchersProvider {

    override fun dispatcher(): CoroutineContext {
        return Dispatchers.Main
    }
}

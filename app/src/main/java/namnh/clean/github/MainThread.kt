package namnh.clean.github

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import namnh.clean.domain.executor.PostExecutionThread
import javax.inject.Inject

class MainThread @Inject constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}

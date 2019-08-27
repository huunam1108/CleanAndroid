package namnh.clean.domain.executor

import io.reactivex.Scheduler

interface PostExcutionThread {
    fun getScheduler(): Scheduler
}
package namnh.clean.github.di

import namnh.clean.data.executor.JobExecutor
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.github.MainThread
import org.koin.dsl.module

val appModule = module {
    // Threading
    single<PostExecutionThread> { /*PostExecutionThread*/ MainThread() }
    single<ThreadExecutor> { /*ThreadExecutor*/ JobExecutor() }
}

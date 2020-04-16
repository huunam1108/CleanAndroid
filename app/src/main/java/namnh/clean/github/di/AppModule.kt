package namnh.clean.github.di

import namnh.clean.data.scheduler.DefaultDispatcher
import namnh.clean.data.scheduler.IODispatcher
import namnh.clean.domain.scheduler.DEFAULT
import namnh.clean.domain.scheduler.DispatchersProvider
import namnh.clean.domain.scheduler.IO
import namnh.clean.domain.scheduler.MAIN
import namnh.clean.github.MainDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<DispatchersProvider>(named(IO)) { IODispatcher() }
    single<DispatchersProvider>(named(DEFAULT)) { DefaultDispatcher() }
    single<DispatchersProvider>(named(MAIN)) { MainDispatcher() }
}

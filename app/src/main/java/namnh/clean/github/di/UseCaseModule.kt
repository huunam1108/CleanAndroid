package namnh.clean.github.di

import namnh.clean.domain.usecase.SearchReposUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SearchReposUseCase(get()) }
}

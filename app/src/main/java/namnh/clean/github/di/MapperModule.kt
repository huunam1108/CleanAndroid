package namnh.clean.github.di

import namnh.clean.github.model.mapper.OwnerModelMapper
import namnh.clean.github.model.mapper.RepoModelMapper
import namnh.clean.github.model.mapper.UserModelMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserModelMapper() }
    single { OwnerModelMapper() }
    single { RepoModelMapper(/*OwnerModelMapper*/get()) }
}

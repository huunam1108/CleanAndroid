package namnh.clean.github.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import namnh.clean.github.di.scope.FragmentScope
import namnh.clean.github.ui.searchrepo.SearchRepoFragment

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun bindSearchRepoFragment(): SearchRepoFragment
}

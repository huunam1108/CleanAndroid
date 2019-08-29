package namnh.clean.github.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import namnh.clean.github.di.ViewModelFactory
import namnh.clean.github.di.ViewModelKey
import namnh.clean.github.ui.searchrepo.SearchRepoViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchRepoViewModel::class)
    internal abstract fun provideSearchRepoViewModel(viewModel: SearchRepoViewModel): ViewModel
}

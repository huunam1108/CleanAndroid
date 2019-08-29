package namnh.clean.github.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import namnh.clean.github.di.scope.ActivityScope
import namnh.clean.github.ui.MainActivity
import namnh.clean.github.ui.MainActivityModule

@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    internal abstract fun bindMainActivity(): MainActivity
}

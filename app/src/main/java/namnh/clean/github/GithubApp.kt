package namnh.clean.github

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import namnh.clean.github.di.DaggerAppComponent

class GithubApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}

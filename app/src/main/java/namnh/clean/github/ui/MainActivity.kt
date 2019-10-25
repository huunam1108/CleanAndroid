package namnh.clean.github.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import namnh.clean.github.R
import namnh.clean.github.ui.searchrepo.SearchRepoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchRepoFragment.newInstance())
                .commitNow()
        }
    }
}

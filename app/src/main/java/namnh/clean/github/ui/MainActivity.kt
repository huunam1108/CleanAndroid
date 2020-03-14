package namnh.clean.github.ui

import android.content.Intent
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

        handleIntent(intent, false)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent, true)
    }

    /**
     * @param intent: the intent start activity
     * @param fromNewIntent: true if click notification when app background, otherwise app is killed.
     */
    private fun handleIntent(intent: Intent?, fromNewIntent: Boolean) {
    }
}

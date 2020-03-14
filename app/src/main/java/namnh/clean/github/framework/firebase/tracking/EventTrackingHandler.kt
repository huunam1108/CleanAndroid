package namnh.clean.github.framework.firebase.tracking

import android.app.Activity
import android.os.Bundle
import androidx.annotation.MainThread

interface EventTrackingHandler {

    fun trackEvent(eventName: String, data: Bundle?)

    @MainThread
    fun trackScreen(activity: Activity, screenName: String?, screenClassOverride: String?)
}

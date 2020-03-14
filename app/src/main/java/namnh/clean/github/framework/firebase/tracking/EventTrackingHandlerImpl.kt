package namnh.clean.github.framework.firebase.tracking

import android.app.Activity
import android.os.Bundle
import androidx.annotation.MainThread
import com.google.firebase.analytics.FirebaseAnalytics

class EventTrackingHandlerImpl(private val firebaseAnalytics: FirebaseAnalytics) :
    EventTrackingHandler {

    /**
     * For tracking an event to firebase.
     * @param eventName: currently use custom event name, @see TrackingEvent.[Name]
     * @param data: the data you wanna track with event.
     */
    override fun trackEvent(eventName: String, data: Bundle?) {
        firebaseAnalytics.logEvent(eventName, data)
    }

    /**
     * Using for track open screen, [https://firebase.google.com/docs/analytics/screenviews]
     *
     * **Important note:**
     *
     *  * This screen must be call on MainThread
     *
     *  @param activity: The activity to which the screen name and class name apply.
     *  @param screenName: The name of the current screen. Set to null to clear the current screen name.
     *  @param screenClassOverride: The name of the screen class. By default this is the class name of the current Activity.
     *  Set to null to revert to the default class name.
     */
    @MainThread
    override fun trackScreen(
        activity: Activity,
        screenName: String?,
        screenClassOverride: String?
    ) {
        firebaseAnalytics.setCurrentScreen(activity, screenName, screenClassOverride)
    }
}

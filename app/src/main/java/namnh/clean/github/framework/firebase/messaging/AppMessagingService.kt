package namnh.clean.github.framework.firebase.messaging

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppMessagingService : FirebaseMessagingService() {

    /**
     * The registration token may change when:
     * *The app deletes Instance ID
     * *The app is restored on a new device
     * *The user uninstalls/reinstall the app
     * *The user clears app data.
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Device token is changed: $token")
        // saveDeviceToken(token)
        // sendDeviceTokenToServer()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
    }

    companion object {
        private const val TAG = "AppMessagingService"
    }
}

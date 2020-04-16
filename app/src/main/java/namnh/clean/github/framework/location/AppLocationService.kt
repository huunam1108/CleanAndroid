package namnh.clean.github.framework.location

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import namnh.clean.data.repository.source.local.api.SharedPrefApi
import namnh.clean.github.R
import namnh.clean.github.ui.MainActivity
import org.koin.android.ext.android.inject

/**
 * This is a long-running service for location updates. When an activity is
 * bound to this service, frequent location updates are permitted. When the activity is removed
 * from the foreground, the service promotes itself to a foreground service, and location updates
 * continue. When the activity comes back to the foreground, the foreground service stops, and the
 * notification associated with that service is removed.
 */
class AppLocationService : Service() {

    private val sharedPrefApi: SharedPrefApi by inject()
    private val serviceBinder = LocalBinder()

    private val notificationManager: NotificationManager? by lazy {
        getSystemService(NOTIFICATION_SERVICE) as? NotificationManager
    }

    private var systemIntentFilter = IntentFilter().apply {
        addAction(LocationManager.PROVIDERS_CHANGED_ACTION)
    }

    private val systemBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                LocationManager.PROVIDERS_CHANGED_ACTION -> {
                    /*if (!isLocationEnabled(applicationContext)) {
                        stopSelf()
                    }*/
                }
            }
        }
    }
    /**
     * Contains parameters used by [com.google.android.gms.location.FusedLocationProviderApi].
     */
    private var locationRequest: LocationRequest? = null

    /**
     * Provides access to the Fused Location Provider API.
     */
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    /**
     * Callback for changes in location.
     */
    private var locationCallback: LocationCallback? = null

    private var serviceHandler: Handler? = null

    /**
     * The current location.
     */
    private var latestLocation: Location? = null

    /**
     * Extra to help us figure out if we arrived in onStartCommand via the notification or not.
     * The PendingIntent that leads to a call to open the app settings
     * The PendingIntent to launch main activity.
     * Returns the [NotificationCompat] used as part of the foreground service.
     */
    private fun locationNotification(): Notification {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", applicationContext?.packageName, null)
        }

        val settingsPendingIntent = PendingIntent.getActivity(
            this, SETTING_REQUEST_CODE, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val activityPendingIntent = PendingIntent.getActivity(
            this, ACTIVITY_REQUEST_CODE, Intent(this, MainActivity::class.java), 0
        )

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .addAction(
                R.drawable.ic_star, "getString(R.string.open_app)",
                activityPendingIntent
            )
            .addAction(
                R.drawable.ic_star,
                "getString(R.string.open_settings)",
                settingsPendingIntent
            )
            .setContentTitle("getString(R.string.notif_title)")
            .setContentText("getString(R.string.notif_content)")
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.ic_star)
            .setTicker(getString(R.string.app_name))
            .setWhen(System.currentTimeMillis())
            .setStyle(NotificationCompat.BigTextStyle())

        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name).plus(CHANNEL_NAME)
            // Create the channel for the notification
            val notificationChannel =
                NotificationChannel(
                    CHANNEL_ID,
                    name,
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "getString(R.string.description)"
                }
            // Set the Notification Channel for the Notification Manager.
            notificationManager?.createNotificationChannel(notificationChannel)
        }

        return notificationBuilder.build()
    }

    override fun onCreate() {
        registerReceiver(systemBroadcastReceiver, systemIntentFilter)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createLocationCallback()
        createLocationRequest()
        getLastLocation()

        val handlerThread = HandlerThread(TAG)
        handlerThread.start()
        serviceHandler = Handler(handlerThread.looper)

        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID, locationNotification())
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service onStartCommand")

        // Tells the system to not try to recreate the service after it has been killed.
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        // Called when a client comes to the foreground and binds with this service.
        // The service should cease to be a foreground service when that happens.
        Log.i(TAG, "in onBind()")
        stopForeground(true)
        return serviceBinder
    }

    override fun onRebind(intent: Intent) {
        // Called when a client returns to the foreground
        // and binds once again with this service. The service should cease to be a foreground
        // service when that happens.
        Log.i(TAG, "in onRebind()")
        stopForeground(true)
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.i(TAG, "Last client unbound from service")

        // Called when the last client unbinds from this service.
        // we make this service a foreground service.
        Log.i(TAG, "Starting foreground service")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // TODO: check the request location condition
            startForeground(NOTIFICATION_ID, locationNotification())
        }
        return true // Ensures onRebind() is called when a client re-binds.
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemBroadcastReceiver)
        serviceHandler?.removeCallbacksAndMessages(null)
    }

    /**
     * Makes a request for location updates.
     */
    fun requestLocationUpdates() {
        Log.i(TAG, "Requesting location updates")
        setRequestingLocationUpdates(true)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            startService(Intent(applicationContext, AppLocationService::class.java))
        }
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        } catch (unlikely: SecurityException) {
            setRequestingLocationUpdates(false)
            Log.e(TAG, "Lost location permission. Could not request updates. $unlikely")
        }
    }

    /**
     * Removes location updates.
     */
    fun removeLocationUpdates() {
        Log.i(TAG, "Removing location updates")
        try {
            fusedLocationClient.removeLocationUpdates(locationCallback)
            setRequestingLocationUpdates(false)
            stopSelf()
        } catch (unlikely: SecurityException) {
            setRequestingLocationUpdates(true)
            Log.e(TAG, "Lost location permission. Could not remove updates. $unlikely")
        }
    }

    private fun getLastLocation() {
        try {
            fusedLocationClient.lastLocation
                .addOnCompleteListener { task ->
                    if (task.isSuccessful && task.result != null) {
                        latestLocation = task.result
                    } else {
                        Log.w(TAG, "Failed to get location.")
                    }
                }
        } catch (unlikely: SecurityException) {
            Log.e(TAG, "Lost location permission.$unlikely")
        }
    }

    private fun onNewLocation(location: Location) {
        Log.i(TAG, "New location: ${location.latitude} - ${location.longitude}")
    }

    /**
     * Sets the location request parameters.
     */
    private fun createLocationRequest() {
        locationRequest = LocationRequest().apply {
            interval = UPDATE_INTERVAL_IN_MILLISECONDS
            fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                onNewLocation(locationResult.lastLocation)
            }
        }
    }

    private fun requestingLocationUpdates(): Boolean {
        return sharedPrefApi.get("REQUESTING_LOCATION", Boolean::class.java) ?: false
    }

    private fun setRequestingLocationUpdates(requestingLocationUpdates: Boolean) {
        sharedPrefApi.put("REQUESTING_LOCATION", requestingLocationUpdates)
    }

    /**
     * Class used for the client Binder.  Since this service runs in the same process as its
     * clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        internal val service: AppLocationService
            get() = this@AppLocationService
    }

    companion object {
        private val TAG = AppLocationService::class.java.simpleName
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = " Location Service"

        /**
         * The desired interval for location updates.
         * Inexact. Updates may be more or less frequent.
         */
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000

        /**
         * The fastest rate for active location updates.
         * Updates will never be more frequent than this value.
         */
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2
        /**
         * The identifier for the notification displayed for the foreground service.
         */
        private const val NOTIFICATION_ID = 12345678

        private const val SETTING_REQUEST_CODE = 0
        private const val ACTIVITY_REQUEST_CODE = 1
    }
}

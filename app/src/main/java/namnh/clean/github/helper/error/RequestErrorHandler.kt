package namnh.clean.github.helper.error

import android.app.Activity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import namnh.clean.data.repository.source.remote.api.error.CloudExceptionMapper

class RequestErrorHandler : ErrorHandler {
    override fun proceed(fragment: Fragment?, error: Throwable?) {
        proceed(fragment?.activity, error)
    }

    override fun proceed(activity: Activity?, error: Throwable?) {
        if (activity == null || activity.isFinishing || error == null) return
        val requestError = CloudExceptionMapper().map(throwable = error)
        requestError.apply {
            if (isNetworkError()) {
                showError(activity, "Check Connection")
            } else if (isServerError()) {
                showError(activity, "Server Problem, try later!")
            } else if (isHttpError() || isUnexpectedError()) {
                showError(activity, "Unexpected error, try later")
            }
        }
    }

    private fun showError(activity: Activity, message: String) {
        Snackbar.make(
            activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE
        ).setAction("OK", null).show()
    }
}

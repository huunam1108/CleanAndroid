package namnh.clean.github.ui.base.binding

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import java.util.*
import namnh.clean.shared.extensions.gone
import namnh.clean.shared.extensions.visible

/**
 * All Data Binding adapters specific to the app should be here.
 */
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("goneIfMatch")
    fun goneIfMatch(view: View, condition: Boolean) {
        if (condition) view.gone() else view.visible()
    }

    @JvmStatic
    @BindingAdapter("visibleIfMatch")
    fun visibleIfMatch(view: View, condition: Boolean?) {
        if (condition == true) view.visible() else view.gone()
    }

    @JvmStatic
    @BindingAdapter("background")
    fun backgroundResource(view: View, @DrawableRes resId: Int) {
        if (view is ImageView) {
            view.setImageResource(resId)
        } else {
            view.setBackgroundResource(resId)
        }
    }
}

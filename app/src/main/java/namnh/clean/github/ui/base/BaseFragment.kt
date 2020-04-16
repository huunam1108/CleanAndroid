package namnh.clean.github.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import namnh.clean.github.framework.firebase.tracking.EventTrackingHandler
import namnh.clean.github.helper.autoCleared
import namnh.clean.github.helper.error.ErrorHandler
import namnh.clean.github.ui.base.binding.FragmentDataBindingComponent
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutResId: Int

    protected var viewDataBinding by autoCleared<DB>()
    protected val requestErrorHandler by inject<ErrorHandler>(named("request"))
    protected val firebaseEventTracking by inject<EventTrackingHandler>(named("firebase"))

    private lateinit var bindingComponent: FragmentDataBindingComponent
    protected val mainHandler = Handler(Looper.getMainLooper())

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingComponent = FragmentDataBindingComponent(this)
        viewDataBinding =
            DataBindingUtil.inflate(inflater, layoutResId, container, false, bindingComponent)
        viewDataBinding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }
}

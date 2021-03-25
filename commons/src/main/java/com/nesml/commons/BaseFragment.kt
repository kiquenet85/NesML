package com.nesml.commons

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nesml.commons.di.common.component.CommonProvider
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.commons.navigation.Navigator

abstract class BaseFragment : Fragment() {

    protected lateinit var resourceManager: ResourceManager
    protected lateinit var errorHandler: ErrorHandler
    protected lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        resourceManager =
            (requireActivity().application as CommonProvider).commonComponent.resourceManager
        errorHandler =
            (requireActivity().application as CommonProvider).commonComponent.errorHandler
        navigator = (requireActivity() as BaseActivity).navigator

        super.onCreate(savedInstanceState)
    }

    protected fun commonHandleUIError(errorState: CommonErrorState) {
        view?.let {
            Snackbar.make(it, errorState.message, Snackbar.LENGTH_SHORT).show()
        }
    }
}
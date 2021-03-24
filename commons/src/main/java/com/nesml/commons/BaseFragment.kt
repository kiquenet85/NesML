package com.nesml.commons

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nesml.commons.di.common.component.CommonProvider
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager

abstract class BaseFragment : Fragment() {

    protected lateinit var resourceManager: ResourceManager
    protected lateinit var errorHandler: ErrorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        resourceManager =
            (requireActivity().application as CommonProvider).commonComponent.resourceManager
        errorHandler =
            (requireActivity().application as CommonProvider).commonComponent.errorHandler
        super.onCreate(savedInstanceState)
    }
}
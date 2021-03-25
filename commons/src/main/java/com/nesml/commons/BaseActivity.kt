package com.nesml.commons

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nesml.commons.di.common.component.CommonProvider
import com.nesml.commons.manager.ResourceManager
import com.nesml.commons.navigation.Navigator
import com.nesml.commons.network.NetworkManager
import com.nesml.commons.network.WifiReceiver

abstract class BaseActivity : AppCompatActivity() {

    val navigator: Navigator = Navigator(this)
    protected lateinit var networkManager: NetworkManager
    protected lateinit var wifiReceiver: WifiReceiver
    protected lateinit var resourceManager: ResourceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        networkManager = (application as CommonProvider).commonComponent.networkManager
        wifiReceiver = (application as CommonProvider).commonComponent.wifiReceiver
        resourceManager = (application as CommonProvider).commonComponent.resourceManager
        super.onCreate(savedInstanceState)
    }

    protected open fun keyboardResizeMode() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}
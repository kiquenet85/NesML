package com.nesml.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nesml.commons.di.common.component.CommonProvider
import com.nesml.commons.navigation.Navigator
import com.nesml.commons.network.NetworkManager
import com.nesml.commons.network.WifiReceiver

abstract class BaseActivity : AppCompatActivity() {

    protected val navigator: Navigator = Navigator(this)
    protected lateinit var networkManager: NetworkManager
    protected lateinit var wifiReceiver: WifiReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        networkManager = (application as CommonProvider).commonComponent.networkManager
        wifiReceiver = (application as CommonProvider).commonComponent.wifiReceiver
        super.onCreate(savedInstanceState)
    }

}
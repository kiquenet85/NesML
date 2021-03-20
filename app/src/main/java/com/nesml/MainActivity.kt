package com.nesml

import android.os.Bundle
import com.nesml.commons.BaseActivity
import com.nesml.commons.BaseFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.navigateTo(BaseFragment())
        }
    }
}
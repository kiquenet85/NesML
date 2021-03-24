package com.nesml

import android.os.Bundle
import com.nesml.commons.BaseActivity
import com.nesml.search_ui.ui.main.feature.list.ui.SearchListActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.navigateTo(this, SearchListActivity::class.java)
        }
    }
}
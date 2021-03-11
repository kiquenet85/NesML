package com.nesml.search_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nesml.search_ui.ui.main.SearchItemFragment

class SearchItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_item_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchItemFragment.newInstance())
                .commitNow()
        }
    }
}
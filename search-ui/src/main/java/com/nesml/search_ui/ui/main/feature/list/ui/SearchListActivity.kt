package com.nesml.search_ui.ui.main.feature.list.ui

import android.os.Bundle
import com.nesml.commons.BaseActivity
import com.nesml.search_ui.R

class SearchListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            navigator.navigateTo(SearchListFragment.newInstance())
        }
    }
}
package com.nesml.search_ui.ui.main.feature.list.ui

import android.os.Bundle
import com.nesml.commons.BaseActivity
import com.nesml.commons.util.EMPTY_STRING
import com.nesml.search_ui.R

class SearchListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            val bundle = intent.extras
            navigator.navigateTo(
                SearchListFragment.newInstance(
                    bundle?.getString(SEARCH_QUERY_KEY) ?: EMPTY_STRING
                ), rootFragment = true
            )
        }
    }

    companion object {
        const val SEARCH_QUERY_KEY = "SEARCH_QUERY_KEY"
    }
}
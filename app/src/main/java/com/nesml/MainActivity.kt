package com.nesml

import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import com.nesml.commons.BaseActivity
import com.nesml.search_ui.ui.main.feature.list.ui.SearchListActivity
import com.nesml.search_ui.ui.main.feature.list.ui.SearchListActivity.Companion.SEARCH_QUERY_KEY

class MainActivity : BaseActivity() {

    private lateinit var searchBox: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyboardResizeMode()
        setContentView(R.layout.activity_main_search)

        searchBox = findViewById(R.id.searchView)
        findViewById<Button>(R.id.search_button).setOnClickListener {
            if (searchBox.query.trim().isNotEmpty()) {
                navigateToSearchResults(searchBox.query.trim().toString())
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.main_search_what_are_you_searching),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Toast.makeText(
            this,
            getString(R.string.main_search_what_are_you_searching),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun navigateToSearchResults(query: String) {
        navigator.navigateTo(
            this,
            SearchListActivity::class.java,
            Bundle().apply { putString(SEARCH_QUERY_KEY, query) })
    }
}
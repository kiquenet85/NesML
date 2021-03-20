package com.nesml.search_ui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nesml.search_ui.R

class SearchItemFragment : Fragment() {

    private lateinit var viewModel: SearchItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = SearchItemFragment()
    }
}
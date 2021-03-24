package com.nesml.search_ui.ui.main.feature.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nesml.commons.BaseFragment
import com.nesml.commons.util.MarginItemDecoration
import com.nesml.search_ui.R
import com.nesml.search_ui.ui.main.di.component.SearchUIProvider
import com.nesml.search_ui.ui.main.feature.list.adapter.SearchItemListAdapter
import com.nesml.search_ui.ui.main.feature.list.use_case.SearchListLoaded

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchListFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchItemAdapter: SearchItemListAdapter
    private val viewModel by viewModels<SearchListViewModel>(factoryProducer = {
        (requireActivity().application as SearchUIProvider).searchUI.viewModelModule
            .provideSearchViewModelFactory((requireActivity() as AppCompatActivity))
    }, ownerProducer = { this })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.search_list_fragment, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchItemResults("Motorola G6")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.searchResults)

        searchItemAdapter = SearchItemListAdapter(resourceManager, errorHandler, mutableListOf())
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.shape_item_divider
            )!!
        )
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(itemDecorator)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.cell_top_margin).toInt(),
                    resources.getDimension(R.dimen.cell_top_margin).toInt(),
                    resources.getDimension(R.dimen.cell_label_margin).toInt()
                )
            )
            recyclerView.adapter = searchItemAdapter
        }

        viewModel.getErrorState().observe(viewLifecycleOwner) { errorState ->
            getView()?.let {
                Snackbar.make(it, errorState.message, Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.getScreenState().observe(viewLifecycleOwner) { searchListState ->
            when (searchListState) {
                is SearchListLoaded -> searchItemAdapter.addNewItems(searchListState.allItems)
            }
        }
    }

    private fun navigateToItemDetail() {

    }

    companion object {
        @JvmStatic
        fun newInstance(): SearchListFragment = SearchListFragment()
    }
}
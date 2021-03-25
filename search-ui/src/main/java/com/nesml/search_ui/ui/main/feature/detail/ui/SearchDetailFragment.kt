package com.nesml.search_ui.ui.main.feature.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nesml.commons.BaseFragment
import com.nesml.commons.util.EMPTY_STRING
import com.nesml.commons.util.MarginItemDecoration
import com.nesml.search_ui.R
import com.nesml.search_ui.ui.main.di.component.SearchUIProvider
import com.nesml.search_ui.ui.main.feature.detail.adapter.SearchItemDetailAdapter
import com.nesml.search_ui.ui.main.feature.detail.use_case.SearchDetailLoaded
import com.nesml.search_ui.ui.main.feature.list.ui.SearchListViewModel
import com.nesml.search_ui.ui.main.util.CircleTransform
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchDetailFragment : BaseFragment() {

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var mercadoPago: TextView
    private lateinit var installments: TextView
    private lateinit var buyingMode: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchItemDetailAdapter: SearchItemDetailAdapter
    private val viewModel by viewModels<SearchListViewModel>(factoryProducer = {
        (requireActivity().application as SearchUIProvider).searchUI.viewModelModule
            .provideSearchViewModelFactory((requireActivity() as AppCompatActivity))
    }, ownerProducer = { requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.search_detail_fragment, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSearchItem(requireArguments().getString(SEARCH_ITEM_ID) ?: EMPTY_STRING)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.itemImage)
        name = view.findViewById(R.id.name)
        price = view.findViewById(R.id.price)
        mercadoPago = view.findViewById(R.id.mercado_pago)
        installments = view.findViewById(R.id.installments)
        buyingMode = view.findViewById(R.id.buying_mode)

        recyclerView = view.findViewById(R.id.attributes)

        searchItemDetailAdapter =
            SearchItemDetailAdapter(resourceManager, errorHandler, mutableListOf())
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
            recyclerView.adapter = searchItemDetailAdapter
        }

        viewModel.getErrorState().observe(viewLifecycleOwner) { errorState ->
            commonHandleUIError(errorState)
        }

        viewModel.getScreenDetailState().observe(viewLifecycleOwner) { searchDetailState ->
            when (searchDetailState) {
                is SearchDetailLoaded -> {
                    (searchDetailState).apply {
                        Picasso.get()
                            .load(item.thumbnail?.replace("http://", "https://") ?: EMPTY_STRING)
                            .centerInside()
                            .resize(150, 150)
                            .transform(CircleTransform())
                            .into(image, object : Callback {
                                override fun onSuccess() {

                                }

                                override fun onError(e: Exception) {
                                    errorHandler.report(e)
                                }
                            })
                        name.text = item.title ?: EMPTY_STRING

                        val priceData =
                            item.price?.toString() ?: item.sale_price
                            ?: item.original_price?.toString()
                        price.text =
                            priceData?.let { resourceManager.formatCurrencyCOP(it.toLong()) }
                                ?: resourceManager.getString(R.string.search_list_no_price_found)

                        mercadoPago.text =
                            if (item.accepts_mercadopago == true) getString(R.string.search_detail_accept_mercado_pago) else getString(
                                R.string.search_detail_NOT_accept_mercado_pago
                            )

                        installments.text = item.installment?.let {
                            if (it.quantity != null && it.amount != null) "${it.quantity} x ${it.amount}"
                            else context?.getString(R.string.search_list_no_installments)
                        }

                        buyingMode.text = item.buying_mode

                        searchItemDetailAdapter.addNewItems(item.attributes ?: emptyList())
                    }
                }
            }
        }
    }

    companion object {
        const val SEARCH_ITEM_ID = "SEARCH_ITEM_ID"

        @JvmStatic
        fun newInstance(searchItemId: String): SearchDetailFragment = SearchDetailFragment().also {
            it.arguments = Bundle().apply {
                putString(SEARCH_ITEM_ID, searchItemId)
            }
        }
    }
}
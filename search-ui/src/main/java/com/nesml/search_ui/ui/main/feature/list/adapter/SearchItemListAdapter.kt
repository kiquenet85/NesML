package com.nesml.search_ui.ui.main.feature.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.search_ui.R
import com.nesml.storage.model.search.entity.SearchItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SearchItemListAdapter(
    private val searchItemListener: SearchItemListener,
    private val resourceManager: ResourceManager,
    private val errorHandler: ErrorHandler,
    private val localDataSet: MutableList<SearchItem>
) :
    RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: ViewGroup = view.findViewById(R.id.container)
        val image: ImageView = view.findViewById(R.id.itemImage)
        val name: TextView = view.findViewById(R.id.name)
        val price: TextView = view.findViewById(R.id.price)
        val installments: TextView = view.findViewById(R.id.installments)
        val arrival: TextView = view.findViewById(R.id.buying_mode)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.name.context
        with(localDataSet[position]) {
            viewHolder.name.text = this.title
            viewHolder.price.text =
                this.price?.toString() ?: this.sale_price ?: this.original_price?.toString()
                        ?: context.getString(R.string.search_list_no_price_found)
            viewHolder.installments.text = this.installment?.let {
                if (it.quantity != null && it.amount != null) "${it.quantity} x ${it.amount}"
                else context.getString(R.string.search_list_no_installments)
            }
            this.condition?.let {
                viewHolder.arrival.text = this.condition
            }
            this.thumbnail?.let {
                val https = it.replace("http://", "https://")
                Picasso.get()
                    .load(https)
                    .into(viewHolder.image, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception) {
                            errorHandler.report(e)
                        }
                    })
            }
            viewHolder.container.setOnClickListener {
                searchItemListener.onSearchItemClick(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return localDataSet.size
    }

    fun addNewItems(allItems: List<SearchItem>) {
        localDataSet.clear()
        localDataSet.addAll(allItems)
        notifyDataSetChanged()
    }

    interface SearchItemListener {
        fun onSearchItemClick(searchItem: SearchItem)
    }
}

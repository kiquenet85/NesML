package com.nesml.search_ui.ui.main.feature.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.search_ui.R
import com.nesml.storage.model.search.entity.Attribute

class SearchItemDetailAdapter(
    private val resourceManager: ResourceManager,
    private val errorHandler: ErrorHandler,
    private val localDataSet: MutableList<Attribute>
) :
    RecyclerView.Adapter<SearchItemDetailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val values: TextView = view.findViewById(R.id.values)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_attribute_detail_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(localDataSet[position]) {
            viewHolder.name.text = this.name
            val values = StringBuilder()
            this.values?.forEach { values.append(it.name) }
            viewHolder.values.text = values.toString()
        }
    }

    override fun getItemCount(): Int {
        return localDataSet.size
    }

    fun addNewItems(allItems: List<Attribute>) {
        localDataSet.clear()
        localDataSet.addAll(allItems)
        notifyDataSetChanged()
    }
}

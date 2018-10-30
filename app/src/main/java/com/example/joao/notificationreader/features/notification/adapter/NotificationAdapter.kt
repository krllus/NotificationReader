package com.example.joao.notificationreader.features.notification.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.joao.notificationreader.commons.adapter.AdapterConstants
import com.example.joao.notificationreader.commons.adapter.ViewType
import com.example.joao.notificationreader.commons.adapter.ViewTypeDelegateAdapter

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
class NotificationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var elemens: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.NOTIFICATION, NotificationDelegateAdapter())
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        elemens = ArrayList()
        elemens.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return elemens.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, elemens[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.elemens[position].getViewType()
    }

    fun updateElements(newElements: List<ViewType>) {
        elemens.addAll(newElements)
        notifyDataSetChanged()
    }
}
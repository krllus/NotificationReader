package com.example.joao.notificationreader.features.notification.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.commons.adapter.ViewType
import com.example.joao.notificationreader.commons.adapter.ViewTypeDelegateAdapter
import com.example.joao.notificationreader.commons.extensions.inflate

/**
 * Created by João Carlos on 10/30/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TurnsViewHolder(parent)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: ViewType
    ) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.notification_item_loading)
    )

}
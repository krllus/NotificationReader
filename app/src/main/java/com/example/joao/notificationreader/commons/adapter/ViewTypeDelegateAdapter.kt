package com.example.joao.notificationreader.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by João Carlos on 10/30/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: ViewType
    )
}
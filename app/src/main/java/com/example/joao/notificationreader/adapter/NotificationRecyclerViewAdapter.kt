package com.example.joao.notificationreader.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.model.NotificationData

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
class NotificationRecyclerViewAdapter : RecyclerView.Adapter<NotificationViewHolder>() {

    private var elements: MutableList<NotificationData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notification, parent, false)


        return NotificationViewHolder(rootView)
    }

    override fun getItemCount(): Int = elements.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindView(elements[holder.adapterPosition])
    }

    fun updateElements(newElements: List<NotificationData>) {
        elements.clear()
        elements.addAll(newElements)
        notifyDataSetChanged()
    }
}
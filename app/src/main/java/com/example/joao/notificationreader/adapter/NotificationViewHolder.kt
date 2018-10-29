package com.example.joao.notificationreader.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.model.NotificationData

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var icon: ImageView = view.findViewById(R.id.notification_icon)
    private var title: TextView = view.findViewById(R.id.notification_title)
    private var time: TextView = view.findViewById(R.id.notification_time)
    private var message: TextView = view.findViewById(R.id.notification_message)

    fun bindView(notification: NotificationData) {
        icon.setImageResource(R.drawable.ic_launcher_foreground)
        title.text = notification.title
        time.text = notification.ticker
        message.text = notification.message
    }

}
package com.example.joao.notificationreader.features.notification.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.commons.adapter.ViewType
import com.example.joao.notificationreader.commons.adapter.ViewTypeDelegateAdapter
import com.example.joao.notificationreader.commons.extensions.inflate
import com.example.joao.notificationreader.features.notification.model.NotificationData
import kotlinx.android.synthetic.main.row_notification.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by João Carlos on 10/30/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
class NotificationDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = NotificationViewHolder(parent)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: ViewType
    ) {
        holder as NotificationViewHolder
        holder.bind(item as NotificationData)
    }

    class NotificationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.row_notification)
    ) {
        private var context: Context = itemView.context
        private var imgIcon: ImageView = itemView.notification_icon
        private var txtTitle: TextView = itemView.notification_title
        private var txtTime: TextView = itemView.notification_time
        private var txtMessage: TextView = itemView.notification_message

        fun bind(notification: NotificationData) = with(notification) {
            imgIcon.setImageDrawable(getPackageDrawable(context, notification.pack))
            txtTitle.text = notification.title
            txtTime.text = getDateString(notification.timeStamp)
            txtMessage.text = notification.message
        }

        private fun getDateString(time: Long): String {
            val simpleDataFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val date = Date(time)
            return simpleDataFormatter.format(date)
        }

        private fun getPackageDrawable(context: Context, packageName: String): Drawable {
            return try {
                context.packageManager.getApplicationIcon(packageName)
            } catch (e: PackageManager.NameNotFoundException) {
                context.getDrawable(R.drawable.ic_launcher_foreground)!!
            }

        }
    }
}
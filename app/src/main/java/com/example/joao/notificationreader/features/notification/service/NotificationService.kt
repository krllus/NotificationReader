package com.example.joao.notificationreader.features.notification.service

import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.joao.notificationreader.features.notification.model.NotificationData
import com.example.joao.notificationreader.features.notification.model.NotificationDatabase
import java.util.concurrent.Executors

class NotificationService : NotificationListenerService() {
    private val tag: String = NotificationService::class.java.simpleName

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val pack = sbn?.packageName
        val ticker = sbn?.notification?.tickerText.toString()

        val extras = sbn?.notification?.extras

        val title = extras!!.getString("android.title")
        val message = extras.getString("android.text")

        //save notification to Database
        val notification = NotificationData()
        notification.pack = pack ?: "default package"
        notification.title = title ?: "default title"
        notification.message = message ?: ticker
        notification.info = ticker

        insertNotificationIntoDatabase(notification)
    }

    private fun insertNotificationIntoDatabase(notificationData: NotificationData) {

        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            val database = NotificationDatabase.getInstance(context)
            database?.notificationDao()
                ?.insert(notificationData)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        Log.i(tag, "Notification Removed")
    }
}

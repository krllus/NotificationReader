package com.example.joao.notificationreader.service

import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.joao.notificationreader.model.NotificationData
import com.example.joao.notificationreader.model.NotificationDatabase


class NotificationService : NotificationListenerService() {
    val tag: String = NotificationService::class.java.simpleName

    private var database : NotificationDatabase? = null

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val pack = sbn?.packageName
        val ticker = sbn?.notification?.tickerText.toString()

        val extras = sbn?.notification?.extras
        val title = extras?.getString("android.title")
        val text = extras?.getCharSequence("android.text")!!.toString()

        val msgrcv = Intent("Msg")
        msgrcv.putExtra("package", pack)
        msgrcv.putExtra("ticker", ticker)
        msgrcv.putExtra("title", title)
        msgrcv.putExtra("text", text)

        //save notification to Database
        val notification = NotificationData()
        notification.pack = pack!!
        notification.ticker = ticker
        notification.title = title!!
        notification.message = text

        insertNotificationIntoDatabase(notification)
    }

    private fun insertNotificationIntoDatabase(notificationData: NotificationData) {
        database?.notificationDao()?.insert(notificationData)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        Log.i(tag, "Notification Removed")
    }
}

package com.example.joao.notificationreader.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.joao.notificationreader.R
import com.example.joao.notificationreader.features.notification.NotificationFragment

class MainActivity : AppCompatActivity() {

    private val channelId: String by lazy {
        getString(R.string.dummy_notification_channel_id)
    }

    private val notificationId: Int by lazy {
        42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!checkNotificationReadPermissionGranted())
            launchSettingsActivity()

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    NotificationFragment.newInstance(),
                    NotificationFragment::class.java.simpleName
                )
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_dummy_notification -> {
                createNotificationSample()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkNotificationReadPermissionGranted(): Boolean {
        return Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
            .contains(packageName)
    }

    private fun launchSettingsActivity() {
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        startActivity(intent)
    }

    private fun createNotificationSample() {

        createNotificationChannel()

        val mBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.notification_small_icon)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mBuilder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.dummy_notification_channel_name)
            val descriptionText = getString(R.string.dummy_notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

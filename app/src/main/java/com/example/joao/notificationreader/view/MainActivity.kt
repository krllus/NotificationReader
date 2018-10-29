package com.example.joao.notificationreader.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import com.example.joao.notificationreader.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!checkNotificationReadPermissionGranted())
            launchSettingsActivity()

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance(), MainFragment::class.java.simpleName)
                .commit()
    }

    private fun checkNotificationReadPermissionGranted(): Boolean {
        return Settings.Secure.getString(contentResolver, "enabled_notification_listeners").contains(packageName)
    }

    private fun launchSettingsActivity() {
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        startActivity(intent)
    }
}

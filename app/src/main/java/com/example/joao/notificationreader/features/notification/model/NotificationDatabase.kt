package com.example.joao.notificationreader.features.notification.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

@Database(entities = [NotificationData::class], version = 3)
abstract class NotificationDatabase : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao

    companion object {
        private var INSTANCE: NotificationDatabase? = null

        fun getInstance(context: Context): NotificationDatabase? {
            if (INSTANCE == null) {
                synchronized(NotificationDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotificationDatabase::class.java, "notification.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}
package com.example.joao.notificationreader.features.notification.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

@Dao
interface NotificationDao {
    @Query(
        "SELECT * FROM notification WHERE notification_id = :id"
    )
    fun getNotificationData(id: String): NotificationData

    @Query("SELECT * FROM notification ORDER BY timeStamp desc")
    fun getNotifications(): List<NotificationData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notificationData: NotificationData)
}
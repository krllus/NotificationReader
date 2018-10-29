package com.example.joao.notificationreader.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification WHERE notification_id = :id")
    fun getNofificationData(id: String): Observable<NotificationData>

    @Query("SELECT * FROM notification")
    fun getNotifications(): Observable<List<NotificationData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notificationData: NotificationData)
}
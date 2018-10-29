package com.example.joao.notificationreader.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

@Entity(tableName = "notification")
data class NotificationData(
    @PrimaryKey(autoGenerate = true) var notification_id: Long?,
    @ColumnInfo(name = "package") var pack: String,
    @ColumnInfo(name = "timestamp") var ticker: String,
    var title: String,
    var message: String
) {
    constructor() : this(null, "", "", "", "")
}
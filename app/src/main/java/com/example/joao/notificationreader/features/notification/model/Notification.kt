package com.example.joao.notificationreader.features.notification.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.joao.notificationreader.commons.adapter.AdapterConstants
import com.example.joao.notificationreader.commons.adapter.ViewType

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

@Entity(tableName = "notification")
data class NotificationData(
    @PrimaryKey(autoGenerate = true) var notification_id: Long? = 0,
    @ColumnInfo(name = "package") var pack: String = "",
    @ColumnInfo(name = "timestamp") var ticker: String = "",
    var title: String = "",
    var message: String = ""
) : ViewType {
    override fun getViewType(): Int {
        return AdapterConstants.NOTIFICATION
    }

    constructor() : this(null, "", "", "", "")
}
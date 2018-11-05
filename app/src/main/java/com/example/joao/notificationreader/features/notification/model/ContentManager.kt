package com.example.joao.notificationreader.features.notification.model

import android.content.Context
import io.reactivex.Observable

/**
 * Created by João Carlos on 10/30/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

class ContentManager {
    fun getNotifications(context: Context?): Observable<List<NotificationData>> {
        val database = NotificationDatabase.getInstance(context!!)
        val dao = database!!.notificationDao()

        return Observable.create { subscriber ->
            subscriber.onNext(dao.getNotifications())
        }
    }
}
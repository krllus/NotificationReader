package com.example.joao.notificationreader.features.notification.model

import android.content.Context
import io.reactivex.Observable

/**
 * Created by João Carlos on 10/30/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

class NotificationManager {
    fun getFakeNotifications(): Observable<List<NotificationData>> {
        return Observable.create { subscriber ->
            val list = mutableListOf<NotificationData>()
            for (i in 1..3) {
                list.add(NotificationData(0, "main", "now", "title $i", "message $i"))
            }
            subscriber.onNext(list)
        }
    }

    fun getNotifications(context: Context?): Observable<List<NotificationData>> {
        val database = NotificationDatabase.getInstance(context!!)
        val dao = database!!.notificationDao()

        return Observable.create { subscriber ->
            subscriber.onNext(dao.getNotifications())
        }
    }
}
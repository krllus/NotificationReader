package com.example.joao.notificationreader.commons.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by João Carlos on 10/30/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot : Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
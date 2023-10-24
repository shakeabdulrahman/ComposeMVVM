package com.example.composemvvm.common

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

object AppSetStatusBarColor {
    fun appSetStatusBarColor(activity: Activity, color: Int) {
        val window = activity.window
        window.statusBarColor = ContextCompat.getColor(activity, color)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }
}


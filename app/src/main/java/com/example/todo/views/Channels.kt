package com.example.todo.views

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

class Channels(private val notificationManager: NotificationManager) {
    private val channels = mutableMapOf<Int, NotificationChannel>()

    operator fun get(importance: Int): String = channelId(importance)
        .also {
            if (oreoOrNewer()) {
                createChannel(it, importance)
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(id: String, importance: Int) =
        channels.computeIfAbsent(importance) {
            NotificationChannel(id, "TODO_CHANNEL", importance).also {
                it.description = "Notification channel where todo ites appear."
                notificationManager.createNotificationChannel(it)
            }
        }

    private fun channelId(importance: Int) = "COM.EXAMPLE.TODO.$importance"

    private fun oreoOrNewer() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}

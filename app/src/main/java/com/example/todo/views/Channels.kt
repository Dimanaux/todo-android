package com.example.todo.views

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

class Channels(private val notificationManager: NotificationManager) {
    val group: String = "com.example.todo.ALL"
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
            NotificationChannel(id, "todo_channel", importance).also {
                it.description = "Notification channel where todo items appear."
                notificationManager.createNotificationChannel(it)
                notificationManager.createNotificationChannelGroup(
                    NotificationChannelGroup(
                        group,
                        "Todo"
                    )
                )
            }
        }

    private fun channelId(importance: Int) = "com.example.todo.$importance"

    private fun oreoOrNewer() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}

package com.example.todo.views

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import com.example.todo.R
import com.example.todo.controllers.DoneBroadcastReceiver

class Notifications(
    private val context: Context,
    private val channels: Channels,
    private val notificationManager: NotificationManager
) {
    fun showNotification(id: Int, title: String, text: String, importance: Int) {
        val builder = notificationBuilder(importance)
            .setContentTitle(title)
            .setContentText(text)
            .addAction(doneAction(id))
        notificationManager.notify(id, builder.build())
    }

    fun cancel(id: Int) {
        notificationManager.cancel(id)
    }

    private fun doneAction(id: Int) =
        NotificationCompat.Action(
            R.mipmap.ic_launcher_round,
            context.getString(R.string.mark_as_done),
            doneIntent(id)
        )

    private fun doneIntent(id: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            id,
            Intent(context, DoneBroadcastReceiver::class.java).apply {
                putExtra("todoId", id)
            },
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun notificationBuilder(importance: Int) =
        NotificationCompat.Builder(context, channels[importance])
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setOngoing(true)
            .setVisibility(VISIBILITY_PUBLIC)
            .setGroup(channels.group)
}

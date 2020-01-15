package com.example.todo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class TodoNotificationView(private val context: Context) {
    private val channelId = "COM.EXAMPLE.TODO.TODOS"
    private var notificationId = 0
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private var _channel: NotificationChannel? = null
    private val channel: NotificationChannel
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            if (_channel == null) {
                _channel = NotificationChannel(
                    channelId,
                    "TODO_CHANNEL",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply { description = "Notification channel where todo ites appear." }
            }
            return _channel!!
        }

    fun showNotification(title: String, message: String) {
        if (oreoOrNewer()) {
            notificationManager.createNotificationChannel(channel)
        }
        val mBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // notification icon
            .setContentTitle(title) // title for notification
            .setContentText(message)// message for notification
            .setAutoCancel(true) // clear notification after click
            .addAction(NotificationCompat.Action(1, "Done!", intent()))
            .setOngoing(true)
        val intent = Intent(context, MainActivity::class.java)
        val pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(pi)
        notificationManager.notify(nextNotificationId(), mBuilder.build())
    }

    private fun nextNotificationId(): Int {
        notificationId += 1
        return notificationId
    }

    private fun intent(): PendingIntent {
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                1,
                Intent(context, MainActivity::class.java),
                0
            )
        return pendingIntent
    }

    private fun oreoOrNewer() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}

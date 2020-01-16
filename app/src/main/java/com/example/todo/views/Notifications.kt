package com.example.todo.views

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.todo.MainActivity
import com.example.todo.R

class Notifications(private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val channels = Channels(notificationManager)

    fun showNotification(id: Int, title: String, text: String, importance: Int) {
        val builder = notificationBuilder(importance)
            .setContentTitle(title)
            .setContentText(text)
            .addAction(NotificationCompat.Action(1, context.getString(R.string.mark_as_done), intent()))
        notificationManager.notify(id, builder.build())
    }

    private fun intent(): PendingIntent {
        return PendingIntent.getActivity(
            context,
            1,
            Intent(context, MainActivity::class.java),
            0
        )
    }

    private fun notificationBuilder(importance: Int) =
        NotificationCompat.Builder(context, channels[importance])
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setOngoing(true)
}

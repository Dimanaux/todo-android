package com.example.todo.views

import android.app.NotificationManager
import android.content.Context
import com.example.todo.model.Todo

class TodoNotificationView(context: Context) {
    private val notifications: Notifications =
        Notifications(context)

    fun showNotificationFor(todo: Todo) {
        val (id, text, priority) = todo
        notifications.showNotification(
            id?.toInt() ?: 0,
            "TODO",
            text,
            todoPriorityToNotificationImportance(priority)
        )
    }

    private fun todoPriorityToNotificationImportance(priority: Int) =
        when (priority) {
            5 -> NotificationManager.IMPORTANCE_HIGH
            -5 -> NotificationManager.IMPORTANCE_LOW
            else -> NotificationManager.IMPORTANCE_DEFAULT
        }
}

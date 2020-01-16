package com.example.todo.views

import android.app.NotificationManager
import com.example.todo.model.Todo

class TodoNotificationView(private val notifications: Notifications) {
    fun showNotification(todo: Todo) {
        val (id, text, priority) = todo
        notifications.showNotification(
            id?.toInt() ?: 0,
            "To Do",
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

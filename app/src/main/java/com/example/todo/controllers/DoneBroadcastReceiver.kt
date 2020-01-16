package com.example.todo.controllers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.todo.model.TodoUpdateInteractor
import com.example.todo.views.TodoNotificationView

class DoneBroadcastReceiver : BroadcastReceiver() {
    private lateinit var todoUpdateInteractor: TodoUpdateInteractor
    private lateinit var notificationView: TodoNotificationView

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }
        val notificationId = intent.getIntExtra("todoId", -1)
        if (notificationId == -1) {
            return
        }
        initialize(context)

        todoUpdateInteractor.markAsDoneById(
            notificationId,
            notificationView::showNotification
        )
    }

    private fun initialize(context: Context) {
        val componentFactory = ComponentFactory(context)
        todoUpdateInteractor = componentFactory.todoUpdateInteractor
        notificationView = componentFactory.todoNotificationView
    }
}

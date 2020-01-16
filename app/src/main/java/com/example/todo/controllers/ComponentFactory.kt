package com.example.todo.controllers

import android.app.NotificationManager
import android.content.Context
import com.example.todo.model.TodoCreateInteractor
import com.example.todo.model.TodoUpdateInteractor
import com.example.todo.persistance.DatabaseFactory
import com.example.todo.views.Channels
import com.example.todo.views.Notifications
import com.example.todo.views.TodoNotificationView

internal class ComponentFactory(private val context: Context) {
    val todoNotificationView by lazy { TodoNotificationView(notifications) }

    val todoCreateInteractor by lazy { TodoCreateInteractor(todoDao) }

    val todoUpdateInteractor by lazy { TodoUpdateInteractor(todoDao) }

    private val todoDao by lazy { databaseFactory.todoDao() }

    private val databaseFactory by lazy { DatabaseFactory.new(context) }

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val channels by lazy { Channels(notificationManager) }

    private val notifications by lazy { Notifications(context, channels, notificationManager) }
}

package com.example.todo.controllers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.dsl.onSubmit
import com.example.todo.model.TodoBootstrapInteractor
import com.example.todo.model.TodoCreateInteractor
import com.example.todo.views.TodoNotificationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notificationView: TodoNotificationView
    private lateinit var todoCreateInteractor: TodoCreateInteractor
    private lateinit var todoBootstrapInteractor: TodoBootstrapInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

        todoInput.onSubmit(this::createTodo)

        todoBootstrapInteractor.bootstrapNotifications(notificationView::show)
    }

    private fun createTodo(text: String) {
        todoCreateInteractor.createNew(text, notificationView::show)
        todoInput.text.clear()
    }

    private fun initialize() {
        val componentFactory = ComponentFactory(this)
        notificationView = componentFactory.todoNotificationView
        todoCreateInteractor = componentFactory.todoCreateInteractor
        todoBootstrapInteractor = componentFactory.todoBootstrapInteractor
    }
}

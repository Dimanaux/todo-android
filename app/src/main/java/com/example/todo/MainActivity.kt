package com.example.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.controllers.TodoController
import com.example.todo.dsl.onSubmit
import com.example.todo.persistance.DatabaseFactory
import com.example.todo.views.TodoNotificationView
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notificationView: TodoNotificationView
    private lateinit var todoController: TodoController
    private lateinit var databaseFactory: DatabaseFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseFactory = DatabaseFactory.new(this)
        notificationView = TodoNotificationView(this)

        todoController = TodoController(notificationView, databaseFactory.todoDao())
        todoInput.onSubmit {
            todoController.createNew(todoInput.text.toString())
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}

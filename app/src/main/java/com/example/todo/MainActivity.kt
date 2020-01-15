package com.example.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.dsl.onSubmit
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notificationView: TodoNotificationView
    private lateinit var todoInteractor: TodoInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationView = TodoNotificationView(this)

        todoInteractor = TodoInteractor(notificationView)
        todoInput.onSubmit {
            todoInteractor.createNew(todoInput.text.toString())
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}

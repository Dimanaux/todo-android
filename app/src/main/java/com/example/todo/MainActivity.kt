package com.example.todo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notificationView: TodoNotificationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationView = TodoNotificationView(this)
        todoCreateButton.setOnClickListener {
            createTodo(todoInput.text.toString())
        }
        todoInput.onSubmit {
            createTodo(todoInput.text.toString())
        }
    }

    private fun createTodo(text: String) {
        notificationView.showNotification("TODO", text)
    }


    private fun EditText.onSubmit(callback: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback()
            }
            return@setOnEditorActionListener true
        }
    }
}

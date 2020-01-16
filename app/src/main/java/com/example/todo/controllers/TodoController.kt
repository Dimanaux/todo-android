package com.example.todo.controllers

import com.example.todo.dsl.save
import com.example.todo.model.Todo
import com.example.todo.persistance.TodoDao
import com.example.todo.views.TodoNotificationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoController(
    private val notificationView: TodoNotificationView,
    private val todoDao: TodoDao
) {
    fun createNew(text: String) {
        if (text.isEmpty()) {
            return
        }
        val todo = Todo(text = text)

        val subscribe = todoDao.save(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(notificationView::showNotificationFor)
    }
}

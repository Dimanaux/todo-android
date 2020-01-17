package com.example.todo.model

import com.example.todo.dsl.findByStatus
import com.example.todo.persistance.TodoDao

class TodoBootstrapInteractor(
    private val todoDao: TodoDao
) {
    fun bootstrapNotifications(whenBootstrapped: (Todo) -> Unit) {
        val disposable = todoDao.findByStatus(TodoStatus.New)
            .subscribe { todos -> todos.forEach(whenBootstrapped) }
    }
}

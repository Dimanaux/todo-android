package com.example.todo.dsl

import com.example.todo.model.TodoStatus
import com.example.todo.persistance.TodoDao

fun TodoDao.findByStatus(status: TodoStatus) =
    findByStatus(status.statusId)

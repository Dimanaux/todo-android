package com.example.todo.dsl

import com.example.todo.model.Todo
import com.example.todo.persistance.TodoDao
import io.reactivex.Single

fun TodoDao.save(todo: Todo): Single<Todo> =
    if (todo.id != null) {
        update(todo)
        Single.just(todo)
    } else {
        insert(todo).map { id ->
            todo.id = id
            return@map todo
        }
    }

package com.example.todo.dsl

import com.example.todo.model.Todo
import com.example.todo.model.TodoStatus
import com.example.todo.persistance.TodoDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun TodoDao.save(todo: Todo): Single<Todo> {
    val todoSingle = if (todo.id != null) {
        update(todo).map { todo }
    } else {
        insert(todo).map { id ->
            todo.id = id
            return@map todo
        }
    }
    return todoSingle
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun TodoDao.findByStatus(status: TodoStatus): Single<List<Todo>> =
    findByStatus(status.statusId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun TodoDao.find(id: Long): Single<Todo> =
    findById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

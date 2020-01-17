package com.example.todo.dsl

import com.example.todo.model.Todo
import com.example.todo.model.TodoStatus
import com.example.todo.persistance.TodoDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun TodoDao.findByStatus(status: TodoStatus): Single<List<Todo>> =
    findByStatus(status.statusId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

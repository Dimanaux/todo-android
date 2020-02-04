package com.example.todo.model

import com.example.todo.dsl.save
import com.example.todo.persistance.TodoDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoCreateInteractor(private val todoDao: TodoDao) {
    fun createNew(text: String, priority: Int = 0, whenCreated: (Todo) -> Unit) {
        if (text.isEmpty()) {
            return
        }
        val todo = Todo(text = text, priority = priority)

        val disposable = todoDao.save(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(whenCreated)
    }
}

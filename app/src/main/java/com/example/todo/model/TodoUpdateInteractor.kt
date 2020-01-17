package com.example.todo.model

import com.example.todo.dsl.find
import com.example.todo.dsl.save
import com.example.todo.persistance.TodoDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoUpdateInteractor(private val todoDao: TodoDao) {
    fun markAsDone(todo: Todo, whenUpdated: (Todo) -> Unit) {
        val disposable = markAsDone(todo)
            .subscribe(whenUpdated)
    }

    fun markAsDoneById(id: Long, whenUpdated: (Todo) -> Unit) {
        val disposable = todoDao.find(id)
            .flatMap(this::markAsDone)
            .subscribe(whenUpdated)
    }

    private fun markAsDone(todo: Todo): Single<Todo> {
        todo.status = TodoStatus.Done
        return todoDao.save(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

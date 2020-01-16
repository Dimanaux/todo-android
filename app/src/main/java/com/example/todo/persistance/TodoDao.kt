package com.example.todo.persistance

import androidx.room.*
import com.example.todo.model.Todo
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo): Single<Long>

    @Query("SELECT * FROM todo;")
    fun findAll(): Flowable<Todo>

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}

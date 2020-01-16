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

    @Query("SELECT * FROM todo WHERE id = :id LIMIT 1;")
    fun findById(id: Int): Single<Todo>

    @Query("SELECT * FROM todo WHERE status = :status")
    fun findByStatus(status: Int): Flowable<Todo>

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}

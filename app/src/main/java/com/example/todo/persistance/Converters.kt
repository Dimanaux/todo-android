package com.example.todo.persistance

import androidx.room.TypeConverter
import com.example.todo.model.TodoStatus

object Converters {
    @TypeConverter
    @JvmStatic
    fun todoStatusToInt(status: TodoStatus) = status.statusId

    @TypeConverter
    @JvmStatic
    fun intToTodoStatus(statusId: Int) = TodoStatus.valueOf(statusId)
}

package com.example.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var text: String,
    var priority: Int = 0,
    var status: TodoStatus = TodoStatus.New
)

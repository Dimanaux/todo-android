package com.example.todo.model

enum class TodoStatus(val statusId: Int) {
    New(0),
    Done(1),
    Failed(2);

    companion object {
        @JvmStatic
        fun valueOf(statusId: Int): TodoStatus {
            return values().find { it.statusId == statusId }!!
        }
    }
}

package com.example.todo

class TodoInteractor(val notificationView: TodoNotificationView) {
    fun createNew(text: String) {
        // TODO: do persistence
        if (text.isNotEmpty()) {
            notificationView.showNotification("TODO", text)
        }
    }
}

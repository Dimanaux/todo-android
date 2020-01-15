package com.example.todo.dsl

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSubmit(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback()
        }
        return@setOnEditorActionListener true
    }
}

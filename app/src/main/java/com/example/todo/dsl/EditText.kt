package com.example.todo.dsl

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSubmit(callback: (String) -> Unit) =
    setOnEditorActionListener { _, action, _ ->
        if (action == EditorInfo.IME_ACTION_DONE) {
            callback(this.text.toString())
        }
        return@setOnEditorActionListener true
    }

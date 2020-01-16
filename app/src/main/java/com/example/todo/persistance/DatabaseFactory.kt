package com.example.todo.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class DatabaseFactory : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private const val databaseName = "todo-db"

        fun new(context: Context) =
            Room.databaseBuilder(
                context,
                DatabaseFactory::class.java,
                databaseName
            ).build()
    }
}

package com.example.task_app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_app.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDau
}
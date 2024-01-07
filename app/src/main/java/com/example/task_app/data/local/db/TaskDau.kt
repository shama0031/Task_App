package com.example.task_app.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.task_app.model.Task

@Dao
interface TaskDau {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>
    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}
package com.tomasmacri.mytodoapp.addtasks.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tomasmacri.mytodoapp.addtasks.ui.data.TaskDao
import com.tomasmacri.mytodoapp.addtasks.ui.data.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
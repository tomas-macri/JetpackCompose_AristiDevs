package com.tomasmacri.mytodoapp.addtasks.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var task: String,
    var done: Boolean = false
)
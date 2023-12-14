package com.tomasmacri.mytodoapp.addtasks.ui.dataclasses

import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task

data class TasksStateDC(
    val tasks: List<Task>? = null,
    val showDialog: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)

fun TasksStateDC.copySuccess(): TasksStateDC = this.copy(error = null)
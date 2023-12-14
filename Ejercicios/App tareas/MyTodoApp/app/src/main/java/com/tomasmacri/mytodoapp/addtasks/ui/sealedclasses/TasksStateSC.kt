package com.tomasmacri.mytodoapp.addtasks.ui.sealedclasses

import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task

sealed class TasksStateSC {
    data object ShowDialog : TasksStateSC()
    class ListLoaded(val list: List<Task>) : TasksStateSC()
    data object Loading : TasksStateSC()
    class Error(val errorMessage: String) : TasksStateSC()
}
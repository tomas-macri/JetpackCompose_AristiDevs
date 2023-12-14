package com.tomasmacri.mytodoapp.addtasks.ui.dataclasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task
import com.tomasmacri.mytodoapp.addtasks.ui.domain.usecases.AddTaskUseCase
import com.tomasmacri.mytodoapp.addtasks.ui.domain.usecases.DeleteTaskUseCase
import com.tomasmacri.mytodoapp.addtasks.ui.domain.usecases.GetTasksUseCase
import com.tomasmacri.mytodoapp.addtasks.ui.domain.usecases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModelDC @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val _taskState: MutableStateFlow<TasksStateDC> = MutableStateFlow(TasksStateDC(loading = true))
    val taskState: StateFlow<TasksStateDC> = _taskState

    init {
        viewModelScope.launch {
            getTasksUseCase()
                .catch {
                  _taskState.value = _taskState.value.copy(error = it.message)
                }
                .collect{
                    //Will only update the UI if any item is different than in the previous list
                    _taskState.value = _taskState.value.copySuccess().copy(tasks = it)
                }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            addTaskUseCase(task)
            _taskState.value = _taskState.value.copySuccess().copy(showDialog = false)
        }
    }

    fun onAddClickChanged() {
        _taskState.value = _taskState.value.copySuccess().copy(showDialog = true)
    }

    fun onTaskChecked(task: Task, newValue: Boolean) {
        viewModelScope.launch { updateTaskUseCase(task.copy(done = newValue)) }
    }
}
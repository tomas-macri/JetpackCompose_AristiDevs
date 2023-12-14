package com.tomasmacri.mytodoapp.addtasks.ui.sealedclasses

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
class TasksViewModelSC @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val _taskState: MutableStateFlow<TasksStateSC> = MutableStateFlow(TasksStateSC.Loading)
    val taskState: StateFlow<TasksStateSC> = _taskState

    init {
        viewModelScope.launch {
            getTasksUseCase()
                .catch {
                  _taskState.value = TasksStateSC.Error(it.message ?: "Unexcpected error")
                }
                .collect{
                    _taskState.value = TasksStateSC.ListLoaded(list = it)
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
        }
    }

    fun onAddClickChanged() {
        _taskState.value = TasksStateSC.ShowDialog
    }

    fun onTaskChecked(task: Task, newValue: Boolean) {
        viewModelScope.launch { updateTaskUseCase(task.copy(done = newValue)) }
    }
}
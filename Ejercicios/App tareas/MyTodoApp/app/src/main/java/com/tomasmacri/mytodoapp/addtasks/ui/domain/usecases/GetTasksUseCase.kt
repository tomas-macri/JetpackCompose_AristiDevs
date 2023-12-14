package com.tomasmacri.mytodoapp.addtasks.ui.domain.usecases

import com.tomasmacri.mytodoapp.addtasks.ui.data.TaskRepository
import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = taskRepository.getAll()
}
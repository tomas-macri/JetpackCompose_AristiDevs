package com.tomasmacri.mytodoapp.addtasks.ui.domain.usecases

import com.tomasmacri.mytodoapp.addtasks.ui.data.TaskRepository
import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: Task) {
        taskRepository.update(taskModel)
    }

}
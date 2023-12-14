package com.tomasmacri.mytodoapp.addtasks.ui.data

import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    fun getAll() =
        taskDao.getTasks().map { items -> items.map { Task(it.id, it.task, it.done) } }

suspend fun add(taskModel: Task) {
    taskDao.addTask(taskModel.toData())
}

suspend fun update(taskModel: Task) {
    taskDao.updateTask(taskModel.toData())
}

suspend fun delete(taskModel: Task) {
    taskDao.deleteTask(taskModel.toData())
}
}

fun Task.toData(): TaskEntity {
    return TaskEntity(this.id, this.task, this.done)
}
package com.tomasmacri.mytodoapp.addtasks.ui.sealedclasses

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tomasmacri.mytodoapp.addtasks.ui.domain.model.Task
import com.tomasmacri.mytodoapp.addtasks.ui.utils.DefaultFAB
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TasksViewModelSC) {
    val state by viewModel.taskState.collectAsStateWithLifecycle()
    var textScreenStates by rememberSaveable {
        mutableStateOf("")
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val corroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = { DefaultFAB { viewModel.onAddClickChanged() } }) { paddingValues ->
        when (val currentState = state) {
            is TasksStateSC.Error -> {
                LaunchedEffect(key1 = true) {
                    corroutineScope.launch {
                        snackbarHostState.showSnackbar(currentState.errorMessage)
                    }
                }
            }
            TasksStateSC.Loading -> Unit
            is TasksStateSC.ListLoaded -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(currentState.list) {
                        TaskItem(
                            it,
                            onLongClick = { _, taskSelected -> viewModel.deleteTask(taskSelected) },
                            onTaskCheckBoxClicked = { taskSelected, newValue -> viewModel.onTaskChecked(taskSelected, newValue) })
                    }
                }
            }

            TasksStateSC.ShowDialog -> AddTaskDialog(
                textScreenStates,
                onUpdateText = { textScreenStates = it },
                onDismissRequest = { viewModel.onAddClickChanged() },
                onAddClicked = {
                    viewModel.addTask(Task(task = textScreenStates))
                    textScreenStates = ""
                })
        }

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddTaskDialog(task: String, onUpdateText: (String) -> Unit, onDismissRequest: () -> Unit, onAddClicked: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), value = task, onValueChange = { onUpdateText(it) },
                placeholder = { Text(text = "Your task...", color = MaterialTheme.colorScheme.primary) })
            Button(
                onClick = { onAddClicked() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Add task")
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onLongClick: (Offset, Task) -> Unit, onTaskCheckBoxClicked: (Task, Boolean) -> Unit) {
    Card(modifier = Modifier
        .padding(12.dp)
        .pointerInput(Unit) {
            detectTapGestures(onLongPress = { onLongClick(it, task) })
        }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = task.task, modifier = Modifier.padding(8.dp))
            Checkbox(checked = task.done, onCheckedChange = { onTaskCheckBoxClicked(task, it) })
        }
    }
}

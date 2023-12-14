package com.tomasmacri.mytodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tomasmacri.mytodoapp.addtasks.ui.dataclasses.TaskScreenDC
import com.tomasmacri.mytodoapp.addtasks.ui.dataclasses.TasksViewModelDC
import com.tomasmacri.mytodoapp.addtasks.ui.sealedclasses.TaskScreen
import com.tomasmacri.mytodoapp.addtasks.ui.sealedclasses.TasksViewModelSC
import com.tomasmacri.mytodoapp.ui.theme.MyTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tasksViewModelSC: TasksViewModelDC by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TaskScreenDC(tasksViewModelSC)
                }
            }
        }
    }
}
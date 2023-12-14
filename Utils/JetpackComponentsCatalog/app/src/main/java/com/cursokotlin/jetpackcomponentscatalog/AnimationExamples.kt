package com.cursokotlin.jetpackcomponentscatalog

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SensorDoor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple() {
    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }
    var showBox by rememberSaveable {
        mutableStateOf(true)
    }
    val realColor by animateColorAsState(
        targetValue = if (firstColor) Color.Red else Color.Yellow,
        animationSpec = tween(durationMillis = 1000),
        finishedListener = { showBox = false }
    )

    if (showBox) {
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor)
            .clickable { firstColor = !firstColor })
    }

}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 500),
        finishedListener = {
            if (!smallSize) {
            }
        })
    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable { smallSize = !smallSize })
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimation() {

    var isVisible by remember { mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Mostrar/Ocultar")
        }

        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun CrossfadeExampleAnimation() {

    var myComponentType: ComponentType by remember {
        mutableStateOf(ComponentType.Text)
    }
    Column(Modifier.fillMaxSize()) {

        Button(onClick = { myComponentType = getComponentTypeRandom() }) {
            Text(text = "Cambiar componente")
        }

        Crossfade(targetState = myComponentType) { myComponentType ->
            when (myComponentType) {
                ComponentType.Image -> Icon(Icons.Default.SensorDoor, contentDescription = "" )
                ComponentType.Text -> Text(text = "SOY UN COMPONENTE")
                ComponentType.Box -> Box(
                    Modifier
                        .size(150.dp)
                        .background(Color.Red))
                ComponentType.Error -> Text(text = "ERRORRRRRRR")
            }
        }
    }
}

fun getComponentTypeRandom():ComponentType{
    return when(nextInt(from = 0, until = 3)){
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> ComponentType.Error
    }
}


enum class ComponentType() {
    Image, Text, Box, Error
}












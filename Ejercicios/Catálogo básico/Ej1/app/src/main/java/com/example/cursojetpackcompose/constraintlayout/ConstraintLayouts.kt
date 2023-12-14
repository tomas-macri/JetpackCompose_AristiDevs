package com.example.cursojetpackcompose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ComplexLayoutEx1(){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Cyan), contentAlignment = Alignment.Center){
            Greeting(name = "1")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.LightGray), contentAlignment = Alignment.Center){
                Greeting(name = "2")
            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.Magenta), contentAlignment = Alignment.Center){
                Greeting(name = "3")
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Green), contentAlignment = Alignment.BottomCenter){
            Greeting(name = "4")
        }
    }

}


@Composable
fun ConstraintLayoutEx2(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxGreen, boxBlack, boxBlue, boxYellow, boxMagenta) = createRefs()
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Black)
            .constrainAs(boxBlack) {
                bottom.linkTo(boxGreen.top)
                end.linkTo(boxGreen.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                bottom.linkTo(boxGreen.top)
                start.linkTo(boxGreen.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                top.linkTo(boxGreen.bottom)
                end.linkTo(boxGreen.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                top.linkTo(boxGreen.bottom)
                start.linkTo(boxGreen.end)
            })

    }
}

@Composable
fun Greeting(name: String){
    Text(text = "Hellos $name!")
}

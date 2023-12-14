package com.example.cursojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cursojetpackcompose.common.MyRingtones
import com.example.cursojetpackcompose.ui.theme.CursoJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CursoJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainCustomDialog()
                }
            }
        }
    }
}

@Composable
private fun MainCustomDialog() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        var show by rememberSaveable {
            mutableStateOf(false)
        }

        TextButton(onClick = { show = true }) {
            Text(text = "Mostrar diálogo")
        }

        MySelectionDialog(show, onDismiss = { show = false })

    }
}


@Composable
private fun MyCustomDialog(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = onDismiss, properties = DialogProperties(dismissOnClickOutside = false)) {

            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    MyTitle("Select backup account")
                    AccountItem(R.drawable.ic_launcher_background, "mailejemplo1@gmail.com")
                    AccountItem(R.drawable.ic_launcher_background, "mailejemplo2@gmail.com")
                    AccountItem(R.drawable.ic_launcher_background, "mailejemplo3@gmail.com")
                }
            }
        }
    }
}

@Composable
private fun MySelectionDialog(show: Boolean, onDismiss: () -> Unit){
    if (show){
        Dialog(onDismissRequest = onDismiss) {
            var status by rememberSaveable {
                mutableStateOf("")
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
                shape = MaterialTheme.shapes.medium) {
                MyTitle(text = "Phone ringtone", modifier = Modifier.padding(8.dp))
                Divider(modifier = Modifier.fillMaxWidth())
                MyRadioButtonsList(MyRingtones.values(), status) { status = it}
                Divider(modifier = Modifier.fillMaxWidth())
                MyTwoEndButtonsRow(Modifier.align(Alignment.End), startButtonText = "CANCEL", endButtonText = "OK")

            }
        }
    }
}

@Composable
private fun MyTwoEndButtonsRow(modifier: Modifier = Modifier, startButtonText: String, endButtonText: String) {
    Row(modifier = modifier) {
        TextButton(onClick = { }) {
            Text(text = startButtonText)
        }
        TextButton(onClick = { }) {
            Text(text = endButtonText)
        }
    }
}

@Composable
fun MyRadioButtonsList(values: Array<MyRingtones>, status: String, onSelected: (String) -> Unit) {
    for (ringtone in values){
        MySimpleRingtoneRadioButton(ringtone, status, onSelected)
    }
}

@Composable
private fun MySimpleRingtoneRadioButton(ringtone: MyRingtones, status: String, onSelected: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(modifier = Modifier.padding(start = 16.dp, end = 24.dp), selected = status == ringtone.ringtoneName, onClick = { onSelected(ringtone.ringtoneName) })
        Text(text = ringtone.ringtoneName, fontSize = 16.sp, fontWeight = FontWeight.Normal)
    }
}

@Composable
fun AccountItem(@DrawableRes drawable: Int, email: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable), contentDescription = "image", modifier = Modifier
                .padding(vertical = 8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, modifier = Modifier.padding(horizontal = 8.dp), fontSize = 14.sp)

    }
}

@Composable
fun MyTitle(text: String, modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = text, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
}


@Preview(showBackground = true, device = Devices.PIXEL_3A, showSystemUi = true)
@Composable
fun MainPreview() {
    CursoJetpackComposeTheme {
        MainCustomDialog()
    }
}


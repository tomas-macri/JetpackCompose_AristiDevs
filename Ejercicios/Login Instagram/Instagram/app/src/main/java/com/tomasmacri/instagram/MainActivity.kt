package com.tomasmacri.instagram

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomasmacri.instagram.ui.theme.InstagramBordersColor
import com.tomasmacri.instagram.ui.theme.InstagramGray
import com.tomasmacri.instagram.ui.theme.InstagramTheme
import com.tomasmacri.instagram.ui.theme.InstagramVeryLightGray
import com.tomasmacri.instagram.ui.theme.InstagranLightBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    InstagramLogin()
                }
            }
        }
    }
}

@Composable
fun InstagramLogin() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }


}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier) {
        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Don't have an account?", fontSize = 12.sp, color = InstagramGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(4.dp))
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Sign up", fontSize = 12.sp, color = InstagranLightBlue, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Composable
private fun Body(modifier: Modifier) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.insta),
            contentDescription = "Logo"
        )

        LoginField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, start = 12.dp, end = 12.dp, top = 12.dp)
                .background(InstagramVeryLightGray),
            placeholder = "Phone number, username or email",
            value = "",
            onValueChange = {})

        LoginField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, start = 12.dp, end = 12.dp, top = 6.dp)
                .background(InstagramVeryLightGray, RoundedCornerShape(3.dp)),
            placeholder = "Password",
            value = "",
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Toggle password visibility")
                }
            }

        ) {}


        TextButton(modifier = Modifier.align(Alignment.End), onClick = { /*TODO*/ }) {
            Text(text = "Forgot password?", color = InstagranLightBlue, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        }


        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .background(color = InstagranLightBlue, shape = RoundedCornerShape(3.dp)),
            colors = ButtonDefaults.buttonColors(InstagranLightBlue),
            onClick = { /*TODO*/ }) {
            Text(text = "Log in")
        }

        LoginDivider()
        Spacer(modifier = modifier.height(16.dp))
        SocialLogin()

    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Image(modifier = Modifier.size(16.dp), painter = painterResource(id = R.drawable.fb), contentDescription = "Login with Facebook")
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Continue as Tomás", color = InstagranLightBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)

    }
}

@Composable
private fun Header(modifier: Modifier) {
//    val activity = LocalContext.current as Activity
    val activity: Activity? = null
    Icon(imageVector = Icons.Default.Clear, contentDescription = "Close", modifier = modifier
        .size(32.dp)
        .clickable { activity?.finish() }
    )
}

@Composable
private fun LoginDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Divider(
            Modifier
                .height(1.dp)
                .weight(1f)
        )
        Text(modifier = Modifier.padding(horizontal = 18.dp), color = InstagramGray, fontWeight = FontWeight.Bold, text = "OR")
        Divider(
            Modifier
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginField(modifier: Modifier, placeholder: String, value: String, trailingIcon: @Composable (() -> Unit)? = null, onValueChange: (String) -> Unit) {
    OutlinedTextField(modifier = modifier,
        textStyle = TextStyle.Default.copy(fontSize = 16.sp),
        placeholder = { Text(text = placeholder, color = InstagramGray) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        value = value,
        trailingIcon = trailingIcon,
        onValueChange = { onValueChange(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun InstagramPreview() {
    InstagramTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            InstagramLogin()
        }
    }
}
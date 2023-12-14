package com.tomasmacri.mytwitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomasmacri.mytwitter.ui.theme.MyTwitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTwitterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF1B2936))
                    ) {
                        MyTweet()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTwitterTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1B2936))
        ) {
            MyTweet()
        }
    }
}

@Composable
fun MyTweet() {
    var isLiked by remember { mutableStateOf(false) }
    var isRetweet by remember { mutableStateOf(false) }
    var isCommented by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile), contentDescription = "profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 12.dp)
        ) {
            UserInfo()
            Text(
                text = "Descripción id w arga sobre dwd texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga dw dadsobre texto",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(10))
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Tweet image",
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TweetIconButton(
                    Icon = {
                        if (isCommented){
                            Icon(painter = painterResource(id = R.drawable.ic_chat_filled), contentDescription = "Chat", tint = Color.Gray)
                        } else{
                            Icon(painter = painterResource(id = R.drawable.ic_chat), contentDescription = "Chat", tint = Color.Gray)
                        }},
                    interactions = if (isCommented) "2" else "1"
                ) {
                    isCommented = isCommented.not()
                }

                TweetIconButton(
                    Icon = {
                        if (isRetweet){
                            Icon(painter = painterResource(id = R.drawable.ic_rt), contentDescription = "Retweet", tint = Color(0xFF00FF27))
                        } else {
                            Icon(painter = painterResource(id = R.drawable.ic_rt), contentDescription = "Retweet", tint = Color.Gray)
                        }
                               },
                    interactions = if (isRetweet) "2" else "1"
                ) {
                    isRetweet = isRetweet.not()
                }

                TweetIconButton(
                    Icon = {
                        if (isLiked) {
                            Icon(painter = painterResource(id = R.drawable.ic_like_filled), contentDescription = "Chat", tint = Color(0xFFFF0000))
                        } else {
                            Icon(painter = painterResource(id = R.drawable.ic_like), contentDescription = "Chat", tint = Color.Gray)
                        }
                    },
                    interactions = if (isLiked) "2" else "1"
                ){
                    isLiked = isLiked.not()
                }
            }
        }
    }

}


@Composable
private fun TweetIconButton(Icon: @Composable () -> Unit, interactions: String = "1", onClick: () -> Unit) {
    IconButton(onClick = {onClick()}) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon()
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = interactions, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
private fun UserInfo() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Tomas", fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "@TomasMacri", fontWeight = FontWeight.Light, color = Color.Gray)
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "4h", fontWeight = FontWeight.Light, color = Color.Gray)
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Icon(painter = painterResource(id = R.drawable.ic_dots), contentDescription = "Tweet options", tint = Color.White)
        }
    }
}


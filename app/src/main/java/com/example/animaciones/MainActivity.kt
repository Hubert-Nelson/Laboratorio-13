package com.example.animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentAnimationScreen()
        }
    }
}

enum class ContentState {
    LOADING,
    CONTENT,
    ERROR
}

@Composable
fun ContentAnimationScreen() {
    var currentState by remember { mutableStateOf(ContentState.LOADING) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { currentState = ContentState.LOADING }) {
                Text("Cargando")
            }
            Button(onClick = { currentState = ContentState.CONTENT }) {
                Text("Contenido")
            }
            Button(onClick = { currentState = ContentState.ERROR }) {
                Text("Error")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                (fadeIn(animationSpec = tween(durationMillis = 500)) + slideInVertically(
                    animationSpec = tween(durationMillis = 500),
                    initialOffsetY = { fullHeight -> fullHeight / 2 }
                ))
                    .togetherWith(
                        fadeOut(animationSpec = tween(durationMillis = 500)) + slideOutVertically(
                            animationSpec = tween(durationMillis = 500),
                            targetOffsetY = { fullHeight -> -fullHeight / 2 }
                        )
                    )
            },
            label = "Content Animation"
        ) { targetState ->
            when (targetState) {
                ContentState.LOADING -> LoadingContent()
                ContentState.CONTENT -> MainContent()
                ContentState.ERROR -> ErrorContent()
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("Cargando...", fontSize = 20.sp, color = Color.DarkGray)
    }
}

@Composable
fun MainContent() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Green.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Text("¡Contenido Listo!", fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun ErrorContent() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Red.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Text("¡Error!", fontSize = 20.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun ContentAnimationScreenPreview() {
    ContentAnimationScreen()
}
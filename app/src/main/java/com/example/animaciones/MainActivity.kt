package com.example.animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateSizeAndPositionE()
        }
    }
}

@Composable
fun AnimateSizeAndPositionE() {
    var isLargeAndMoved by remember { mutableStateOf(false) }

    val animatedSize: Dp by animateDpAsState(
        targetValue = if (isLargeAndMoved) 250.dp else 100.dp,
        animationSpec = tween(durationMillis = 700)
    )

    val animatedOffsetX: Dp by animateDpAsState(
        targetValue = if (isLargeAndMoved) 50.dp else 0.dp,
        animationSpec = tween(durationMillis = 700)
    )
    val animatedOffsetY: Dp by animateDpAsState(
        targetValue = if (isLargeAndMoved) 100.dp else 0.dp,
        animationSpec = tween(durationMillis = 700)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isLargeAndMoved = !isLargeAndMoved }) {
            Text("Mover y Cambiar Tamaño")
        }

        Spacer(modifier = Modifier.size(24.dp))

        Box(
            modifier = Modifier
                .offset(x = animatedOffsetX, y = animatedOffsetY) 
                .size(animatedSize)
                .background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimateSizeAndPositionEPreview() {
    AnimateSizeAndPositionE()
}
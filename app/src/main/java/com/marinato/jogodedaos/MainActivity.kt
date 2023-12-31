package com.marinato.jogodedaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marinato.jogodedaos.ui.theme.JogoDeDaosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JogoDeDaosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}
fun DrawScope.circle(offset: (Float) -> Offset) {
    val radius = Dp(20f).value
    drawCircle(
        Color.Black,
        radius = radius,
        center = offset(radius)
    )
}
fun DrawScope.center() { //OK
    circle {
        Offset((size.width / 2f) + (it / 2f),  (size.height / 2) + (it / 2f))
    }
}
fun DrawScope.centerRight() { //OK
    circle {
        Offset((size.width - it),  (size.height / 2) + (it / 2f))
    }
}
fun DrawScope.centerLeft() { //OK
    circle {
        Offset(( it * 2f),  (size.height / 2f) + (it / 2f))
    }
}
fun DrawScope.topRight() { //OK
    circle {
        Offset(size.width - it, (it * 2))
    }
}
fun DrawScope.topLeft() {
    circle {
        Offset(it * 2, it * 2)
    }
}
fun DrawScope.bottomRight() {  //OK
    circle {
        Offset(size.width - it, size.height - it)
    }
}
fun DrawScope.bottomLeft() { //OK
    circle {
        Offset(it * 2f, size.height - it)
    }
}
fun DrawScope.bullet(number: Int) {
    when (number) {
        1 -> {
            center()
        }

        2 -> {
            topRight()
            bottomLeft()
        }
        3 -> {
            center()
            topRight()
            bottomLeft()
        }
        4 -> {
            topRight()
            topLeft()
            bottomRight()
            bottomLeft()
        }
        5 -> {
            topRight()
            topLeft()
            center()
            bottomRight()
            bottomLeft()
        }
        6 -> {
            topRight()
            topLeft()
            centerRight()
            centerLeft()
            bottomRight()
            bottomLeft()
        }
    }
}
@Composable
fun Dice(number: Int, modifier: Modifier) {
    Canvas(
        modifier = modifier
            .size((100).dp, (100).dp)
    ) {
        drawRoundRect(
            Color.Yellow,
            cornerRadius = CornerRadius(20f, 20f),
            topLeft = Offset(10f, 10f),
            size = size
        )
        bullet(number = number)
    }
}
@Composable
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.Black)

    ) {

        Dice(1, Modifier.align(Alignment.TopStart))
        Dice(2, Modifier.align(Alignment.TopCenter))
        Dice(3, Modifier.align(Alignment.TopEnd))
        Dice(4, Modifier.align(Alignment.TopStart).offset(y = 120.dp))
        Dice(5, Modifier.align(Alignment.TopCenter).offset(y = 120.dp))
        Dice(6, Modifier.align(Alignment.TopEnd).offset(y = 120.dp))

        Button(
            onClick = { }, modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (100).dp)
        ) {
            Text(text = "Rolar")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JogoDeDaosTheme {
        App()
    }
}
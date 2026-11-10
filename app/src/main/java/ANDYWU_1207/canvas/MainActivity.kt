package ANDYWU_1207.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import ANDYWU_1207.canvas.ui.theme.CANVASTheme
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CANVASTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestCanvas(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TestCanvas(modifier: Modifier = Modifier) {
    val minionsImage: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.minions)

    var offsetPosition by remember { mutableStateOf(Offset.Zero) }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val newX = offsetPosition.x + dragAmount.x
                    val newY = offsetPosition.y + dragAmount.y

                    if (newX > size.width || newY > size.height || newX < -minionsImage.width || newY < -minionsImage.height) {
                        offsetPosition = Offset.Zero
                    } else {
                        offsetPosition = Offset(newX, newY)
                    }
                }
            }
    ) {
        drawCircle(
            color = Color.Magenta,
            radius = 100f,
            center = Offset(100f, 100f)
        )

        drawRect(
            color = Color.Blue,
            topLeft = Offset(300f, 200f),
            size = Size(200f, 200f),
            style = Stroke(width = 10f)
        )

        drawLine(
            Color.Red,
            Offset(200f, 800f),
            Offset(size.width, size.height),
            strokeWidth = 20f
        )

        val minionsWidth = minionsImage.width.toFloat()
        val minionsHeight = minionsImage.height.toFloat()

        val offsetX = ((size.width - minionsWidth) / 2f) + offsetPosition.x
        val offsetY = ((size.height - minionsHeight) / 2f) + offsetPosition.y

        translate(offsetX, offsetY) {
            drawImage(minionsImage)
        }
    }
}
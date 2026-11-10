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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
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
    // 載入圖片資源 (小小兵)
    val minionsImage: ImageBitmap =
        ImageBitmap.imageResource(id = R.drawable.minions)

    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        //繪製洋紅色的圓, 半徑 100, 圓心 (100,100)
        drawCircle(
            color = Color.Magenta,
            radius = 100f,
            center = Offset(100f, 100f)
        )

        //繪製藍色空心矩形, 左上角 (300,200), 寬度與高度 200
        drawRect(
            color = Color.Blue,
            topLeft = Offset(300f, 200f),
            size = Size(200f, 200f),
            style = Stroke(width = 10f) // 使用 style 控制外框樣式
        )
        // 繪製紅色的線， 從 (200,800) 畫到右下角, 線條粗細 10
        // size.width：畫布的總寬度（Float）
        // size.height：畫布的總高度（Float)

        drawLine(
            Color.Red,
            Offset(200f, 800f),
            Offset(size.width, size.height),
            strokeWidth = 10f
        )
        //取得小小兵圖檔的寬度與高度
        val minionsWidth = minionsImage.width
        val minionsHeight = minionsImage.height

        // 置中公式：(畫布寬高 - 圖片寬高) / 2f
        val offsetX = (size.width - minionsWidth) / 2f
        val offsetY = (size.height - minionsHeight) / 2f

        // 將畫布起點移至置中位置後畫圖
        translate(offsetX, offsetY) {
            drawImage(minionsImage)
        }

    }
}



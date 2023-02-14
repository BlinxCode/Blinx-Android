package com.android.blinxapp.dashboard.ui.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.blinxapp.ui.theme.white


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarqueFadingText (text: String = String(), modifier: Modifier = Modifier){
    val edgeWidth = 70.dp

    Text(
        text,
        modifier
            .widthIn(max = edgeWidth * 4)
            // Rendering to an offscreen buffer is required to get the faded edges' alpha to be
            // applied only to the text, and not whatever is drawn below this composable (e.g. the
            // window).
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent {
                drawContent()
                drawFadedEdge(leftEdge = true)
                drawFadedEdge(leftEdge = false)
            }
            .basicMarquee(
                // Animate forever.
                iterations = Int.MAX_VALUE,
                spacing = MarqueeSpacing(0.dp)
            )
            .padding(start = edgeWidth),
        white,
        fontSize = 12.sp
    )
}

fun ContentDrawScope.drawFadedEdge(leftEdge: Boolean) {
    val edgeWidth = 32.dp

    val edgeWidthPx = edgeWidth.toPx()
    drawRect(
        topLeft = Offset(if (leftEdge) 0f else size.width - edgeWidthPx, 0f),
        size = Size(edgeWidthPx, size.height),
        brush = Brush.horizontalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startX = if (leftEdge) 0f else size.width,
            endX = if (leftEdge) edgeWidthPx else size.width - edgeWidthPx
        ),
        blendMode = BlendMode.DstIn
    )
}
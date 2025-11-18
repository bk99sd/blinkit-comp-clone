package com.example.blinkitclone.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.theme.BlinkitCloneTheme
import kotlin.math.roundToInt

@Composable
fun ScrollingProductBackground(
    modifier: Modifier = Modifier
) {
    // Define three rows of product colors
    val row1Colors = listOf(
        R.color.productLightBlue,
        R.color.productLightYellow,
        R.color.productLightCyan,
        R.color.productLightOrange
    )

    val row2Colors = listOf(
        R.color.productCream,
        R.color.productLightPink,
        R.color.productLightRed,
        R.color.productLightTeal
    )

    val row3Colors = listOf(
        R.color.productLightBrown,
        R.color.productLightGreen,
        R.color.productLightPurple,
        R.color.productLightGray
    )

    // Calculate dimensions: itemSize (140dp) + spacing (16dp) = 156dp per item
    // For 4 colors, one complete cycle = 4 * 156 = 624dp
    val itemWidth = 156f // 140dp item + 16dp spacing
    val colorsPerRow = 4
    val cycleWidth = itemWidth * colorsPerRow // 624dp

    // Create infinite transition for animation
    val infiniteTransition = rememberInfiniteTransition(label = "scroll_animation")

    // Animate offset for continuous scrolling - animate exactly one cycle
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -cycleWidth,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "offset_animation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
            .background(colorResource(R.color.white))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // First row
            ScrollingRow(
                colors = row1Colors,
                offsetX = offsetX,
                itemCount = 50
            )

            // Second row (slightly different speed)
            ScrollingRow(
                colors = row2Colors,
                offsetX = offsetX * 0.85f,
                itemCount = 50
            )

            // Third row (different speed)
            ScrollingRow(
                colors = row3Colors,
                offsetX = offsetX * 1.15f,
                itemCount = 50
            )
        }
    }
}

@Composable
private fun ScrollingRow(
    colors: List<Int>,
    offsetX: Float,
    itemCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(offsetX.roundToInt(), 0) },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Iterate over a large number of items and select colors using modulo
        (0 until itemCount).forEach { index ->
            val colorRes = colors[index % colors.size]
            ProductCard(colorRes = colorRes)
        }
    }
}

@Composable
private fun ProductCard(colorRes: Int) {
    Box(
        modifier = Modifier
            .size(140.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(colorResource(colorRes)),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder for product images
        // You can add Image composables here when you have the actual product images
    }
}

@Preview(showBackground = true, name = "Scrolling Product Background")
@Composable
fun ScrollingProductBackgroundPreview() {
    BlinkitCloneTheme {
        ScrollingProductBackground()
    }
}

package com.example.blinkitclone.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.theme.BlinkitCloneTheme
import kotlinx.coroutines.launch

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

@Composable
fun ScrollingProductBackground(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .background(colorResource(R.color.white)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // First row
        ScrollingRow(
            colors = row1Colors,
        )

        // Second row (slightly different speed)
        ScrollingRow(
            colors = row2Colors,
        )

        // Third row (different speed)
        ScrollingRow(
            colors = row3Colors,
        )
    }
}

@Composable
private fun ScrollingRow(
    colors: List<Int>,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val itemCount = Int.MAX_VALUE

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // Calculate total pixels to scroll
            val totalScroll = 6000f // Adjust based on your content

            while (true) {
                // Slow scroll over 15 seconds
                listState.animateScrollBy(
                    value = totalScroll,
                    animationSpec = tween(
                        durationMillis = 15000,
                        easing = LinearEasing,
                    )
                )
            }
        }
    }
    LazyRow(
        state = listState,
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Iterate over a large number of items and select colors using modulo
        items(count = itemCount, key = { index -> index }) { index ->
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

package com.example.blinkitclone.ui

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.ItemPopupVM.CartItemBrief
import com.example.blinkitclone.ui.theme.Typography

val colors = listOf(
    R.color.teal_200,
    R.color.teal_700,
    R.color.purple_200,
    R.color.purple_500,
    R.color.teal_200,
    R.color.teal_700,
    R.color.purple_200,
    R.color.purple_500,
    R.color.teal_200,
    R.color.teal_700,
    R.color.purple_200,
    R.color.purple_500,
    R.color.teal_200,
    R.color.teal_700,
    R.color.purple_200,
    R.color.purple_500,
    R.color.teal_200,
    R.color.teal_700,
    R.color.purple_200,
    R.color.purple_500,
    R.color.teal_200,
    R.color.teal_700,
    R.color.purple_200,
    R.color.purple_500,
)

@Composable
fun CartItemPop(
    text: String,
    items: List<CartItemBrief>,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Button(
            onClick = {
                onAdd()
            },
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
            )
        }
        if (items.isNotEmpty()) {
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.medium_spacing)))
            CartCard(
                items = items,
            )
        }
    }
}

@Composable
fun CartCard(
    items: List<CartItemBrief>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = colorResource(R.color.green),
                shape = RoundedCornerShape(50.dp),
            )
            .clip(RoundedCornerShape(50.dp))
            .padding(
                vertical = dimensionResource(R.dimen.medium_spacing),
                horizontal = 8.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SwapStack1(
            limit = 3,
            items = items,
        )

        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.medium_spacing)))

        Column {
            Text(
                text = "View Cart",
                style = Typography.titleMedium,
                color = colorResource(R.color.white),
            )
            Text(
                text = "${items.size} items",
                style = Typography.bodySmall,
                color = colorResource(R.color.white),
            )
        }

        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.medium_spacing)))

        CircularImage(
            imageRes = R.drawable.ic_launcher_foreground,
            backgroundColor = colorResource(R.color.colorTranslucent),
        )

    }
}

@Composable
fun SwapStack(
    limit: Int,
    modifier: Modifier = Modifier,
    items: List<CartItemBrief>,
) {
    val overlap = 20
    val itemSize = 40
    val visibleItems = items.sortedBy { it.id }.takeLast(limit)
    val negativeSpace = overlap * (visibleItems.size - 1)

    Box(
        modifier = modifier.width((itemSize + negativeSpace).dp),
    ) {
        visibleItems.forEachIndexed{ index, item ->
            val xOffset by animateDpAsState(targetValue = ((-overlap * (visibleItems.size - 1 - index)).dp))
            CircularImage(
                imageRes = R.drawable.drone,
                modifier = Modifier
                    .offset(x = xOffset)
                    .zIndex(index.toFloat()),
                backgroundColor = colorResource(colors[(item.id).mod(colors.size)]),
            )
        }
    }
}

@Composable
fun SwapStack1(
    limit: Int,
    modifier: Modifier = Modifier,
    items: List<CartItemBrief>,
) {
    val visibleItems = items.sortedBy { it.id }.takeLast(limit)
    val itemSize = 40.dp
    val overlap = 20.dp

    Layout(
        content = {
            visibleItems.forEachIndexed { index, item ->
                CircularImage(
                    imageRes = R.drawable.drone,
                    modifier = Modifier.size(itemSize),
                    backgroundColor = colorResource(colors[item.id % colors.size])
                )
            }
        },
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }

        val totalWidth = itemSize.roundToPx() + (visibleItems.size - 1) * overlap.roundToPx()
        val totalHeight = itemSize.roundToPx()

        layout(totalWidth, totalHeight) {
            placeables.forEachIndexed { index, placeable ->
                val x = index * (itemSize.roundToPx() - overlap.roundToPx())
                placeable.placeRelative(x, 0)
            }
        }
    }
}

@Composable
fun CircularImage(
    image: Bitmap,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .size(40.dp)
                .background(
                    color = colorResource(R.color.white),
                    shape = CircleShape,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = null,
            modifier =
                Modifier
                    .padding(
                        dimensionResource(R.dimen.large_spacing),
                    )
                    .align(Alignment.Center),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CircularImage(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(colors.random()),
) {
    Box(
        modifier =
            modifier
                .size(40.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape,
                )
                .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier =
                Modifier
                    .align(Alignment.Center),
            contentScale = ContentScale.Inside,
        )
    }
}

@Composable
@Preview
private fun PreviewCircularImage() {
    CircularImage(imageRes = R.drawable.ic_launcher_foreground)
}

@Composable
@Preview
private fun PreviewSwapStack() {
    SwapStack1(
        limit = 3,
        items = listOf(
            CartItemBrief(
                id = 1,
            ),
            CartItemBrief(
                id = 2,
            ),
            CartItemBrief(
                id = 3,
            ),
            CartItemBrief(
                id = 4,
            )
        )
    )
}

@Composable
@Preview
private fun PreviewItemPop() {
    val viewModel = ItemPopupVM()
    CartItemPop(
        text = "Cart",
        items = viewModel.items,
        onAdd = {
            viewModel.add(
                CartItemBrief(
                    id = (viewModel.lastId() + 1),
                ),
            )
        },
    )
}
package com.example.blinkitclone.ui.components.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.components.cart.ItemPopupVM.CartItemBrief
import com.example.blinkitclone.ui.components.common.CircularImage
import com.example.blinkitclone.ui.theme.Typography
import kotlinx.coroutines.delay

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
    items: List<CartItemBrief>,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {},
) {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.xgiant_spacing)
            ),
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
            Button(
                onClick = {
                    onRemove()
                },
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                )
            }
        }
        if (items.isNotEmpty()) {
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.medium_spacing)))
            visible = true
        } else {
            visible = false
        }

        CartCard(
            items = items,
            visible = visible,
        )
    }
}

@Composable
fun CartCard(
    items: List<CartItemBrief>,
    visible: Boolean,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            delay(500) // Wait 500ms before expanding
            expanded = true
        } else {
            expanded = false
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(500, easing = FastOutSlowInEasing),
            initialOffsetY = { it }, // Slide from bottom (full height offset)
        ),
    ) {
        Row(
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(
                    color = colorResource(R.color.green),
                    shape = RoundedCornerShape(50.dp),
                )
                .clip(RoundedCornerShape(50.dp))
                .padding(
                    vertical = dimensionResource(R.dimen.medium_spacing),
                    horizontal = dimensionResource(R.dimen.medium_spacing),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            SwapStack(
                limit = 3,
                items = items,
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandHorizontally(
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.size(dimensionResource(R.dimen.medium_spacing)))

                    Column {
                        Text(
                            text = stringResource(R.string.label_view_cart),
                            style = Typography.titleMedium,
                            color = colorResource(R.color.white),
                        )
                        Text(
                            text = stringResource(R.string.label_item_count, items.size),
                            style = Typography.bodySmall,
                            color = colorResource(R.color.white),
                        )
                    }

                    Spacer(modifier = Modifier.size(dimensionResource(R.dimen.medium_spacing)))

                    CircularImage(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        backgroundColor = colorResource(R.color.colorTranslucent),
                    )
                }
            }
        }
    }
}

@Composable
fun SwapStack(
    limit: Int,
    items: List<CartItemBrief>,
    modifier: Modifier = Modifier,
    overlap: Dp = 20.dp,
    itemSize: Dp = 40.dp,
) {
    val visibleItems = items.sortedBy { it.id }.takeLast(limit)

    Box(
        modifier = modifier.width(itemSize + (overlap * (visibleItems.size - 1))),
    ) {
        visibleItems.forEachIndexed{ index, item ->
            val xOffset by animateDpAsState(targetValue = ((overlap * index)))
            CircularImage(
                imageRes = R.drawable.drone,
                modifier = Modifier.offset(x = xOffset),
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
@Preview
private fun PreviewCircularImage() {
    CircularImage(imageRes = R.drawable.ic_launcher_foreground)
}

@Composable
@Preview
private fun PreviewSwapStack() {
    SwapStack(
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
        items = viewModel.items,
        onAdd = {
            viewModel.add(
                CartItemBrief(
                    id = (viewModel.lastId() + 1),
                ),
            )
        },
        onRemove = {
            viewModel.remove(
                CartItemBrief(
                    id = (viewModel.lastId()),
                ),
            )
        }
    )
}

@Composable
@Preview
private fun PreviewCartCard() {
    val viewModel = ItemPopupVM()
    CartCard(
        items = viewModel.items,
        visible = true,
    )
}
package com.example.blinkitclone.ui.components.common

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.components.cart.colors

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
    backgroundColor: Color = colorResource(R.color.white),
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
fun CircularImage(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(R.color.white),
    contentColor: Color = colorResource(R.color.white),
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
            imageVector = imageVector,
            contentDescription = null,
            modifier =
                Modifier
                    .align(Alignment.Center),
            contentScale = ContentScale.Inside,
            colorFilter = ColorFilter.tint(contentColor),
        )
    }
}
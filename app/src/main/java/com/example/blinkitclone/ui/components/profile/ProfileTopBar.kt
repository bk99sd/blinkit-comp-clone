package com.example.blinkitclone.ui.components.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.components.common.CircularImage

@Composable
fun ProfileTopBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(dimensionResource(R.dimen.medium_spacing)),
        contentAlignment = Alignment.Center,
    ) {
        CircularImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            imageVector = Icons.Default.AccountCircle,
            backgroundColor = colorResource(R.color.colorTranslucent),
        )
    }
}

@Composable
@Preview
fun PreviewProfileTopBar() {
    ProfileTopBar(
        modifier = Modifier.width(60.dp)
    )
}
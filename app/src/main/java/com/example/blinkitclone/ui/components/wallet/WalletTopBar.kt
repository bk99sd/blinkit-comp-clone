package com.example.blinkitclone.ui.components.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.components.common.CircularImage
import com.example.blinkitclone.ui.theme.Typography

@Composable
fun WalletTopBar(
    modifier: Modifier = Modifier,
    amount: Double = 0.0,
) {
    var iconHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(
        modifier = modifier.padding(dimensionResource(R.dimen.medium_spacing))
            .onSizeChanged { size ->
                iconHeight = with(density) { size.height.toDp() }
            },
        contentAlignment = Alignment.TopCenter,
    ) {
        CircularImage(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            imageRes = R.drawable.ic_wallet,
            backgroundColor = colorResource(R.color.colorTranslucent),
        )

        Box(
            modifier = Modifier
                .offset(y = iconHeight - iconHeight/4)
                .background(
                    color = colorResource(R.color.black),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 0.dp),
                text = "$$amount",
                style = Typography.labelSmall,
                color = colorResource(R.color.white),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview
private fun PreviewWalletTopBar() {
    WalletTopBar(
        modifier = Modifier.width(60.dp)
    )
}
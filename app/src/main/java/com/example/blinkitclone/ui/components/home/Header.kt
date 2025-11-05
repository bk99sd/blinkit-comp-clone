package com.example.blinkitclone.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import com.example.blinkitclone.ui.components.profile.ProfileTopBar
import com.example.blinkitclone.ui.components.wallet.WalletTopBar
import com.example.blinkitclone.ui.theme.Typography

@Composable
fun Header(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.medium_spacing)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.medium_spacing)
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_spacing))
        ) {
            Text(
                text = "Blinkit in",
                style = Typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                color = colorResource(R.color.white),
            )
            Text(
                text = "8 minutes",
                style = Typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = colorResource(R.color.white),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_spacing))
            ) {
                Text(
                    text = "HOME",
                    style = Typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = colorResource(R.color.white),
                )
                Text(
                    text = "- Floor 3rd, Apartment",
                    style = Typography.labelMedium,
                    color = colorResource(R.color.white),
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                )
            }
        }

        WalletTopBar(modifier = Modifier.width(60.dp))
        ProfileTopBar(modifier = Modifier.width(60.dp))
    }
}

@Composable
@Preview
fun PreviewHeader(
    modifier: Modifier = Modifier,
) {
    Header()
}
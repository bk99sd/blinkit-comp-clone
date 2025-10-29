package com.example.blinkitclone.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R

@Composable
fun CircularProfileText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.colorDarkGray),
        )
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = text,
            style = typography.titleMedium.copy(
                color = colorResource(R.color.colorDarkGray),
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

@Composable
fun CircularProfileText2(
    modifier: Modifier = Modifier,
    text: String,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = text,
            style = typography.titleMedium.copy(
                color = colorResource(R.color.colorDarkGray),
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

@Preview
@Composable
private fun PreviewProfile() {
    CircularProfileText(
        text = "PW",
    )
}

@Preview
@Composable
private fun PreviewProfile2() {
    CircularProfileText2(
        text = "AB",
    )
}
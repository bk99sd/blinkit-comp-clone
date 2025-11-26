package com.example.blinkitclone.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultiProductItem(
    category: BestSellerCategory,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(120.dp)
            .clickable { onCategoryClick(category.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 2x2 Grid for products wrapped in Box for overlay
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // First row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ProductImageBox(
                            imageRes = category.products.getOrNull(0)?.imageRes ?: 0,
                            modifier = Modifier.weight(1f)
                        )
                        ProductImageBox(
                            imageRes = category.products.getOrNull(1)?.imageRes ?: 0,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Second row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ProductImageBox(
                            imageRes = category.products.getOrNull(2)?.imageRes ?: 0,
                            modifier = Modifier.weight(1f)
                        )
                        ProductImageBox(
                            imageRes = category.products.getOrNull(3)?.imageRes ?: 0,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                // Overlay for "more items" indicator at bottom center
                if (category.moreCount > 0) {
                    Box(
                        modifier = Modifier
                            .offset(y = 12.dp)
                            .padding(bottom = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                color = Color.Gray.copy(alpha = 0.5f)
                            ) // Semi-transparent black
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "+${category.moreCount} more",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Category name
            Text(
                text = category.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color(0xFF1A1A1A),
                lineHeight = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ProductImageBox(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF8F8F8))
            .border(
                width = 0.5.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (imageRes != 0) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
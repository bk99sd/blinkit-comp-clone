package com.example.blinkitclone.ui.home

import androidx.annotation.DrawableRes

/**
 * Represents a product in a bestseller category
 */
data class BestSellerProduct(
    val id: String,
    @DrawableRes val imageRes: Int,
    val name: String
)

/**
 * Represents a bestseller category with its products
 */
data class BestSellerCategory(
    val id: String,
    val name: String,
    val products: List<BestSellerProduct>,
    val moreCount: Int = 0
)

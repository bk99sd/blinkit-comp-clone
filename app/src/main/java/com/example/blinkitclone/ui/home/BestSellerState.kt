package com.example.blinkitclone.ui.home

/**
 * State for the BestSeller section
 */
data class BestSellerState(
    val categories: List<BestSellerCategory> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

/**
 * Events for the BestSeller section
 */
sealed interface BestSellerEvent {
    data class OnCategoryClick(val categoryId: String) : BestSellerEvent
    data class OnProductClick(val productId: String) : BestSellerEvent
}

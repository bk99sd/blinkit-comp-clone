package com.example.blinkitclone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blinkitclone.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Interface for BestSeller ViewModel to allow preview implementations
 */
interface IBestSellerViewModel {
    val state: StateFlow<BestSellerState>
    fun onEvent(event: BestSellerEvent)
}

class BestSellerViewModel : ViewModel(), IBestSellerViewModel {

    private val _state = MutableStateFlow(BestSellerState())
    override val state: StateFlow<BestSellerState> = _state.asStateFlow()

    init {
        loadBestSellers()
    }

    override fun onEvent(event: BestSellerEvent) {
        when (event) {
            is BestSellerEvent.OnCategoryClick -> {
                // Handle category click - navigate to category page
                handleCategoryClick(event.categoryId)
            }

            is BestSellerEvent.OnProductClick -> {
                // Handle product click - navigate to product detail
                handleProductClick(event.productId)
            }
        }
    }

    private fun loadBestSellers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            // Simulate loading delay
            kotlinx.coroutines.delay(300)

            val categories = getDummyBestSellers()
            _state.value = _state.value.copy(
                categories = categories,
                isLoading = false
            )
        }
    }

    private fun handleCategoryClick(categoryId: String) {
        // TODO: Navigate to category detail page
        println("Category clicked: $categoryId")
    }

    private fun handleProductClick(productId: String) {
        // TODO: Navigate to product detail page
        println("Product clicked: $productId")
    }

    /**
     * Returns dummy data for bestseller categories
     */
    private fun getDummyBestSellers(): List<BestSellerCategory> {
        return listOf(
            BestSellerCategory(
                id = "1",
                name = "Vegetables & Fruits",
                moreCount = 101,
                products = listOf(
                    BestSellerProduct("p1", R.drawable.img, "Green Leafy"),
                    BestSellerProduct("p2", R.drawable.img, "Onion"),
                    BestSellerProduct("p3", R.drawable.img, "Potato"),
                    BestSellerProduct("p4", R.drawable.img, "Spinach")
                )
            ),
            BestSellerCategory(
                id = "2",
                name = "Chips & Namkeen",
                moreCount = 359,
                products = listOf(
                    BestSellerProduct("p5", R.drawable.img, "Lays Classic"),
                    BestSellerProduct("p6", R.drawable.img, "Kurkure"),
                    BestSellerProduct("p7", R.drawable.img, "Haldirams"),
                    BestSellerProduct("p8", R.drawable.img, "Bingo")
                )
            ),
            BestSellerCategory(
                id = "3",
                name = "Drinks & Juices",
                moreCount = 167,
                products = listOf(
                    BestSellerProduct("p9", R.drawable.img, "Coca Cola"),
                    BestSellerProduct("p10", R.drawable.img, "Pepsi"),
                    BestSellerProduct("p11", R.drawable.img, "Sprite"),
                    BestSellerProduct("p12", R.drawable.img, "Fanta")
                )
            ),
            BestSellerCategory(
                id = "4",
                name = "Oil, Ghee & Masala",
                moreCount = 242,
                products = listOf(
                    BestSellerProduct("p13", R.drawable.img, "Fortune Oil"),
                    BestSellerProduct("p14", R.drawable.img, "Amul Ghee"),
                    BestSellerProduct("p15", R.drawable.img, "MDH Masala"),
                    BestSellerProduct("p16", R.drawable.img, "Everest")
                )
            ),
            BestSellerCategory(
                id = "5",
                name = "Dairy, Bread & Eggs",
                moreCount = 47,
                products = listOf(
                    BestSellerProduct("p17", R.drawable.img, "Amul Milk"),
                    BestSellerProduct("p18", R.drawable.img, "Britannia Bread"),
                    BestSellerProduct("p19", R.drawable.img, "Eggs"),
                    BestSellerProduct("p20", R.drawable.img, "Paneer")
                )
            ),
            BestSellerCategory(
                id = "6",
                name = "Sweets & Chocolates",
                moreCount = 107,
                products = listOf(
                    BestSellerProduct("p21", R.drawable.img, "Cadbury Dairy Milk"),
                    BestSellerProduct("p22", R.drawable.img, "Kit Kat"),
                    BestSellerProduct("p23", R.drawable.img, "5 Star"),
                    BestSellerProduct("p24", R.drawable.img, "Snickers")
                )
            )
        )
    }
}

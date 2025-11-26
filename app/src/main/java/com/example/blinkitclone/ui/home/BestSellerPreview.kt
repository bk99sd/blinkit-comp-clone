package com.example.blinkitclone.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinkitclone.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ============================================================================
// PREVIEW: Full BestSeller Component (Success State)
// ============================================================================

@Preview(
    name = "BestSeller - Full Component",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
fun BestSellerPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(
            modifier = Modifier.padding(vertical = 16.dp),
            viewModel = PreviewBestSellerViewModel()
        )
    }
}

// ============================================================================
// PREVIEW: BestSeller - Loading State
// ============================================================================

@Preview(
    name = "BestSeller - Loading State",
    showBackground = true,
    widthDp = 360,
    heightDp = 400
)
@Composable
fun BestSellerLoadingPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(
            viewModel = PreviewBestSellerViewModel(
                previewState = BestSellerState(isLoading = true)
            )
        )
    }
}

// ============================================================================
// PREVIEW: BestSeller - Error State
// ============================================================================

@Preview(
    name = "BestSeller - Error State",
    showBackground = true,
    widthDp = 360,
    heightDp = 400
)
@Composable
fun BestSellerErrorPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(
            viewModel = PreviewBestSellerViewModel(
                previewState = BestSellerState(errorMessage = "Failed to load bestsellers")
            )
        )
    }
}

// ============================================================================
// PREVIEW: BestSeller - Empty State
// ============================================================================

@Preview(
    name = "BestSeller - Empty State",
    showBackground = true,
    widthDp = 360,
    heightDp = 400
)
@Composable
fun BestSellerEmptyPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(
            viewModel = PreviewBestSellerViewModel(
                previewState = BestSellerState(categories = emptyList())
            )
        )
    }
}

// ============================================================================
// PREVIEW: BestSeller - Tablet (Large Screen)
// ============================================================================

@Preview(
    name = "BestSeller - Tablet",
    showBackground = true,
    widthDp = 800,
    heightDp = 600,
    device = "spec:width=800dp,height=1280dp,dpi=240"
)
@Composable
fun BestSellerTabletPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(
            viewModel = PreviewBestSellerViewModel()
        )
    }
}

// ============================================================================
// PREVIEW: Single MultiProductItem - Vegetables & Fruits
// ============================================================================

@Preview(
    name = "MultiProductItem - Vegetables & Fruits",
    showBackground = true,
    widthDp = 140,
    heightDp = 180
)
@Composable
fun MultiProductItemVegetablesPreview() {
    Surface(
        modifier = Modifier.padding(8.dp),
        color = Color(0xFFF8F8F8)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val category = BestSellerCategory(
                id = "1",
                name = "Vegetables & Fruits",
                moreCount = 101,
                products = listOf(
                    BestSellerProduct("p1", R.drawable.img, "Green Leafy"),
                    BestSellerProduct("p2", R.drawable.img, "Onion"),
                    BestSellerProduct("p3", R.drawable.img, "Potato"),
                    BestSellerProduct("p4", R.drawable.img, "Spinach")
                )
            )

            MultiProductItem(
                category = category,
                onCategoryClick = {}
            )
        }
    }
}

// ============================================================================
// PREVIEW: Single MultiProductItem - Chips & Namkeen
// ============================================================================

@Preview(
    name = "MultiProductItem - Chips & Namkeen",
    showBackground = true,
    widthDp = 140,
    heightDp = 180
)
@Composable
fun MultiProductItemChipsPreview() {
    Surface(
        modifier = Modifier.padding(8.dp),
        color = Color(0xFFF8F8F8)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val category = BestSellerCategory(
                id = "2",
                name = "Chips & Namkeen",
                moreCount = 359,
                products = listOf(
                    BestSellerProduct("p5", R.drawable.img, "Lays"),
                    BestSellerProduct("p6", R.drawable.img, "Kurkure"),
                    BestSellerProduct("p7", R.drawable.img, "Haldirams"),
                    BestSellerProduct("p8", R.drawable.img, "Bingo")
                )
            )

            MultiProductItem(
                category = category,
                onCategoryClick = {}
            )
        }
    }
}

// ============================================================================
// PREVIEW: Single MultiProductItem - Drinks & Juices
// ============================================================================

@Preview(
    name = "MultiProductItem - Drinks & Juices",
    showBackground = true,
    widthDp = 140,
    heightDp = 180
)
@Composable
fun MultiProductItemDrinksPreview() {
    Surface(
        modifier = Modifier.padding(8.dp),
        color = Color(0xFFF8F8F8)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val category = BestSellerCategory(
                id = "3",
                name = "Drinks & Juices",
                moreCount = 167,
                products = listOf(
                    BestSellerProduct("p9", R.drawable.img, "Coca Cola"),
                    BestSellerProduct("p10", R.drawable.img, "Pepsi"),
                    BestSellerProduct("p11", R.drawable.img, "Sprite"),
                    BestSellerProduct("p12", R.drawable.img, "Fanta")
                )
            )

            MultiProductItem(
                category = category,
                onCategoryClick = {}
            )
        }
    }
}

// ============================================================================
// PREVIEW: Single MultiProductItem - No More Count
// ============================================================================

@Preview(
    name = "MultiProductItem - No More Count",
    showBackground = true,
    widthDp = 140,
    heightDp = 180
)
@Composable
fun MultiProductItemNoMoreCountPreview() {
    Surface(
        modifier = Modifier.padding(8.dp),
        color = Color(0xFFF8F8F8)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val category = BestSellerCategory(
                id = "4",
                name = "Limited Stock",
                moreCount = 0, // No more count
                products = listOf(
                    BestSellerProduct("p13", R.drawable.img, "Product 1"),
                    BestSellerProduct("p14", R.drawable.img, "Product 2"),
                    BestSellerProduct("p15", R.drawable.img, "Product 3"),
                    BestSellerProduct("p16", R.drawable.img, "Product 4")
                )
            )

            MultiProductItem(
                category = category,
                onCategoryClick = {}
            )
        }
    }
}

// ============================================================================
// PREVIEW: Single MultiProductItem - Long Category Name
// ============================================================================

@Preview(
    name = "MultiProductItem - Long Name",
    showBackground = true,
    widthDp = 140,
    heightDp = 180
)
@Composable
fun MultiProductItemLongNamePreview() {
    Surface(
        modifier = Modifier.padding(8.dp),
        color = Color(0xFFF8F8F8)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val category = BestSellerCategory(
                id = "5",
                name = "Oil, Ghee & Masala Spices",
                moreCount = 242,
                products = listOf(
                    BestSellerProduct("p17", R.drawable.img, "Fortune Oil"),
                    BestSellerProduct("p18", R.drawable.img, "Amul Ghee"),
                    BestSellerProduct("p19", R.drawable.img, "MDH Masala"),
                    BestSellerProduct("p20", R.drawable.img, "Everest")
                )
            )

            MultiProductItem(
                category = category,
                onCategoryClick = {}
            )
        }
    }
}

// ============================================================================
// PREVIEW: MultiProductItem - Different More Counts
// ============================================================================

@Preview(
    name = "MultiProductItem - Various More Counts",
    showBackground = true,
    widthDp = 360,
    heightDp = 200,
    showSystemUi = false
)
@Composable
fun MultiProductItemVariousCountsPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {
            // Small count
            MultiProductItem(
                category = BestSellerCategory(
                    id = "1",
                    name = "Category A",
                    moreCount = 7,
                    products = listOf(
                        BestSellerProduct("p1", R.drawable.img, "Product 1"),
                        BestSellerProduct("p2", R.drawable.img, "Product 2"),
                        BestSellerProduct("p3", R.drawable.img, "Product 3"),
                        BestSellerProduct("p4", R.drawable.img, "Product 4")
                    )
                ),
                onCategoryClick = {}
            )

            // Large count
            MultiProductItem(
                category = BestSellerCategory(
                    id = "2",
                    name = "Category B",
                    moreCount = 999,
                    products = listOf(
                        BestSellerProduct("p5", R.drawable.img, "Product 5"),
                        BestSellerProduct("p6", R.drawable.img, "Product 6"),
                        BestSellerProduct("p7", R.drawable.img, "Product 7"),
                        BestSellerProduct("p8", R.drawable.img, "Product 8")
                    )
                ),
                onCategoryClick = {}
            )
        }
    }
}

// ============================================================================
// PREVIEW: BestSeller Grid - Different Densities
// ============================================================================

@Preview(
    name = "BestSeller - Low Density (MDPI)",
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
fun BestSellerMdpiPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(viewModel = PreviewBestSellerViewModel())
    }
}

@Preview(
    name = "BestSeller - High Density (XXHDPI)",
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    device = "spec:width=360dp,height=640dp,dpi=480"
)
@Composable
fun BestSellerXxhdpiPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(viewModel = PreviewBestSellerViewModel())
    }
}

// ============================================================================
// PREVIEW: Dark Mode Support (Future Enhancement)
// ============================================================================

@Preview(
    name = "BestSeller - Dark Theme",
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BestSellerDarkPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1A1A1A)
    ) {
        BestSeller(viewModel = PreviewBestSellerViewModel())
    }
}

// ============================================================================
// PREVIEW: Small Screen (Compact Phone)
// ============================================================================

@Preview(
    name = "BestSeller - Small Phone",
    showBackground = true,
    widthDp = 320,
    heightDp = 568,
    device = "spec:width=320dp,height=568dp,dpi=160"
)
@Composable
fun BestSellerSmallPhonePreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8)
    ) {
        BestSeller(viewModel = PreviewBestSellerViewModel())
    }
}

// ============================================================================
// Preview ViewModel for Compose Previews
// ============================================================================

class PreviewBestSellerViewModel(
    private val previewState: BestSellerState = BestSellerState(
        categories = getPreviewCategories(),
        isLoading = false
    )
) : IBestSellerViewModel {

    private val _state = MutableStateFlow(previewState)
    override val state: StateFlow<BestSellerState> = _state.asStateFlow()

    override fun onEvent(event: BestSellerEvent) {
        // No-op for preview
    }

    companion object {
        private fun getPreviewCategories(): List<BestSellerCategory> {
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
                        BestSellerProduct("p5", R.drawable.img, "Lays"),
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
                        BestSellerProduct("p21", R.drawable.img, "Dairy Milk"),
                        BestSellerProduct("p22", R.drawable.img, "Kit Kat"),
                        BestSellerProduct("p23", R.drawable.img, "5 Star"),
                        BestSellerProduct("p24", R.drawable.img, "Snickers")
                    )
                )
            )
        }
    }
}

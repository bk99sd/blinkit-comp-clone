package com.example.blinkitclone.ui.components.cart

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemPopupVM: ViewModel() {
    val items = mutableStateListOf<CartItemBrief>()

    private var lastItemRemoved: Int? = null

    private val _uiState = MutableStateFlow<ItemPopupState>(
        ItemPopupState(
            items = items,
        )
    )
    val uiState = _uiState.asStateFlow()

    fun add(item: CartItemBrief) {
        items.add(item)
        _uiState.value = ItemPopupState(
            items = items,
            itemAdded = item,
        )
    }

    fun lastId(): Int {
        if (items.isNotEmpty()) {
            return items.last().id.toInt()
        }
        return 0
    }

    fun remove(item: CartItemBrief) {
        lastItemRemoved = items.indexOf(item)
        items.remove(item)
        _uiState.value = ItemPopupState(
            items = items,
            itemRemoved = item,
        )
    }

    fun getLastItemRemoved(): Int? {
        val item = lastItemRemoved
        lastItemRemoved = null
        return item
    }

    fun clear() {
        items.clear()
        _uiState.value = ItemPopupState(
            items = items,
        )
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return items.isNotEmpty()
    }

    fun size(): Int {
        return items.size
    }

    fun contains(item: CartItemBrief): Boolean {
        return items.contains(item)
    }

    fun containsAll(items: List<CartItemBrief>): Boolean {
        return this.items.containsAll(items)
    }

    fun indexOf(item: CartItemBrief): Int {
        return items.indexOf(item)
    }

    fun lastIndexOf(item: CartItemBrief): Int {
        return items.lastIndexOf(item)
    }

    data class ItemPopupState(
        val items: List<CartItemBrief>,
        val itemAdded: CartItemBrief? = null,
        val itemRemoved: CartItemBrief? = null,
    )

    data class CartItemBrief(
        val id: Int,
        val image: Bitmap? = null,
    )
}
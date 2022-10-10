package com.solodilov.ecommerceapp.presentation.productlist

import com.solodilov.ecommerceapp.domain.entity.Product

sealed class ProductListScreenState {

    object Loading : ProductListScreenState()
    data class Content(val content: List<Product>) : ProductListScreenState()
    object Error : ProductListScreenState()

}
package com.solodilov.ecommerceapp.presentation.productdetail

import com.solodilov.ecommerceapp.domain.entity.Product

sealed class ProductDetailScreenState {

    object Loading : ProductDetailScreenState()
    data class Content(val content: Product) : ProductDetailScreenState()
    object Error : ProductDetailScreenState()

}
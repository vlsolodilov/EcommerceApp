package com.solodilov.ecommerceapp.domain.entity

data class CartProduct(
    val id: Int,
    val title: String,
    val price: Int,
    val quantity: Int,
    val total: Int,
)
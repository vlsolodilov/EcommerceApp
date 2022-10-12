package com.solodilov.ecommerceapp.domain.entity

data class Cart(
    val products: List<CartProduct>,
    val total: Int,
)
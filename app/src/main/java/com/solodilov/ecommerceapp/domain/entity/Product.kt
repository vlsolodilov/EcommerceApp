package com.solodilov.ecommerceapp.domain.entity

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val brand: String,
    val category: Category,
    val thumbnail: String,
    val images: List<String>,
)
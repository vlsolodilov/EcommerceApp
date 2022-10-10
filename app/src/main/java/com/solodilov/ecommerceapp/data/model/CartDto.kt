package com.solodilov.ecommerceapp.data.model


import com.google.gson.annotations.SerializedName

data class CartDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val products: List<CartProductDto>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("discountedTotal")
    val discountedTotal: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int
)
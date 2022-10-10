package com.solodilov.ecommerceapp.data.model


import com.google.gson.annotations.SerializedName

data class CartProductDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("discountedPrice")
    val discountedPrice: Int
)
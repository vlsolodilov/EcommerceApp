package com.solodilov.ecommerceapp.data.model


import com.google.gson.annotations.SerializedName

data class AllCartsDto(
    @SerializedName("carts")
    val carts: List<CartDto>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)
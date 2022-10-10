package com.solodilov.ecommerceapp.data.model


import com.google.gson.annotations.SerializedName

data class AllProductsDto(
    @SerializedName("products")
    val products: List<ProductDto>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)
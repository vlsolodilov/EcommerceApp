package com.solodilov.ecommerceapp.data.network

import com.solodilov.ecommerceapp.data.model.AllProductsDto
import com.solodilov.ecommerceapp.data.model.AuthDto
import com.solodilov.ecommerceapp.data.model.LoggedInUserDto
import com.solodilov.ecommerceapp.data.model.ProductDto
import retrofit2.http.*

interface DummyJsonApi {

    @POST("login")
    suspend fun login(@Body auth: AuthDto): LoggedInUserDto

    @GET("products")
    suspend fun getProductList(): AllProductsDto

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @GET("products/category/{category}")
    suspend fun getProductListByCategory(@Path("category") category: String): AllProductsDto
}
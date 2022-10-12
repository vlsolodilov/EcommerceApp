package com.solodilov.ecommerceapp.data.datasource

import com.solodilov.ecommerceapp.data.model.AllCartsDto
import com.solodilov.ecommerceapp.data.model.CartDto
import com.solodilov.ecommerceapp.data.model.CartProductDto
import com.solodilov.ecommerceapp.data.model.ProductDto
import com.solodilov.ecommerceapp.domain.entity.Category

interface ProductDataSource {

    suspend fun getProduct(id: Int): ProductDto
    suspend fun getProductList(): List<ProductDto>
    suspend fun getProductListByCategory(category: Category): List<ProductDto>
    suspend fun getCartsOfUser(id: Int): CartDto?
}
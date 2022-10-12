package com.solodilov.ecommerceapp.domain.repository

import com.solodilov.ecommerceapp.domain.entity.Cart
import com.solodilov.ecommerceapp.domain.entity.Category
import com.solodilov.ecommerceapp.domain.entity.Product

interface ProductRepository {
    suspend fun getProduct(id: Int): Product
    suspend fun getProductListByCategory(category: Category): List<Product>
    suspend fun getCart(): Cart?
}
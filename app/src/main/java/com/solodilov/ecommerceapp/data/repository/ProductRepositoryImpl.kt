package com.solodilov.ecommerceapp.data.repository

import com.solodilov.ecommerceapp.data.datasource.ProductDataSource
import com.solodilov.ecommerceapp.data.mapper.ProductMapper
import com.solodilov.ecommerceapp.data.preferences.Preferences
import com.solodilov.ecommerceapp.domain.entity.Cart
import com.solodilov.ecommerceapp.domain.entity.CartProduct
import com.solodilov.ecommerceapp.domain.entity.Category
import com.solodilov.ecommerceapp.domain.entity.Product
import com.solodilov.ecommerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val preferences: Preferences,
    private val mapper: ProductMapper,
) : ProductRepository {
    override suspend fun getProduct(id: Int): Product =
        mapper.mapProductDtoToProduct(dataSource.getProduct(id))

    override suspend fun getProductListByCategory(category: Category): List<Product> =
        if (category == Category.ALL) {
            dataSource.getProductList().map { productDto ->
                mapper.mapProductDtoToProduct(productDto)
            }
        } else {
            dataSource.getProductListByCategory(category).map { productDto ->
                mapper.mapProductDtoToProduct(productDto)
            }
        }

    override suspend fun getCart(): Cart? {
        val userId = preferences.getUserId()
        if (userId == -1) return null
        val cartProductList = dataSource.getCartsOfUser(userId)?.products?.map { cartProductDto ->
            mapper.mapCartProductDtoToCartProduct(cartProductDto)
        } ?: emptyList()
        return Cart(
            products = cartProductList,
            total = dataSource.getCartsOfUser(userId)?.total ?: 0
        )
    }
}
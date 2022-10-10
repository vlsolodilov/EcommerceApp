package com.solodilov.ecommerceapp.data.repository

import com.solodilov.ecommerceapp.data.datasource.ProductDataSource
import com.solodilov.ecommerceapp.data.mapper.ProductMapper
import com.solodilov.ecommerceapp.domain.entity.Category
import com.solodilov.ecommerceapp.domain.entity.Product
import com.solodilov.ecommerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
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
}
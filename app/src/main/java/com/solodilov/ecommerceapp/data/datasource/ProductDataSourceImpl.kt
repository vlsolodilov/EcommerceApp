package com.solodilov.ecommerceapp.data.datasource

import com.solodilov.ecommerceapp.data.model.ProductDto
import com.solodilov.ecommerceapp.data.network.DummyJsonApi
import com.solodilov.ecommerceapp.domain.entity.Category
import javax.inject.Inject

class ProductDataSourceImpl@Inject constructor(
    private val api: DummyJsonApi,
) : ProductDataSource {

    override suspend fun getProduct(id: Int): ProductDto =
        api.getProductById(id)

    override suspend fun getProductList(): List<ProductDto> =
        api.getProductList().products

    override suspend fun getProductListByCategory(category: Category): List<ProductDto> =
        api.getProductListByCategory(category.apiName).products

}
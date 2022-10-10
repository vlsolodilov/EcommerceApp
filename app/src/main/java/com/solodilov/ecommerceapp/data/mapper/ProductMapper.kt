package com.solodilov.ecommerceapp.data.mapper

import com.solodilov.ecommerceapp.data.model.ProductDto
import com.solodilov.ecommerceapp.domain.entity.Category
import com.solodilov.ecommerceapp.domain.entity.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapProductDtoToProduct(productDto: ProductDto): Product =
        Product(
            id = productDto.id,
            title = productDto.title,
            description = productDto.description,
            price = productDto.price,
            brand = productDto.brand,
            category = Category.values().firstOrNull { it.apiName == productDto.category }
                ?: Category.ALL,
            thumbnail = productDto.thumbnail,
            images = productDto.images
        )



}
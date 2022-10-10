package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.entity.Category
import com.solodilov.ecommerceapp.domain.entity.Product
import com.solodilov.ecommerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListByCategoryUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(category: Category): List<Product> =
        productRepository.getProductListByCategory(category)
}
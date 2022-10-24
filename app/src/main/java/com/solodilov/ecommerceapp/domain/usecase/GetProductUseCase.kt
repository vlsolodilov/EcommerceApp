package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.entity.Product
import com.solodilov.ecommerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(id: Int): Product =
        productRepository.getProduct(id)
}
package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.entity.Cart
import com.solodilov.ecommerceapp.domain.entity.CartProduct
import com.solodilov.ecommerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): Cart? =
        productRepository.getCart()
}
package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.repository.UserRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(): String? =
        userRepository.getToken()
}
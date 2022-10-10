package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(token: String) =
        userRepository.saveToken(token)
}
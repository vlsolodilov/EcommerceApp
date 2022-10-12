package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Int) =
        userRepository.saveUserId(userId)
}
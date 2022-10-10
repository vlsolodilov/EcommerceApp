package com.solodilov.ecommerceapp.domain.usecase

import com.solodilov.ecommerceapp.domain.entity.Result
import com.solodilov.ecommerceapp.domain.entity.User
import com.solodilov.ecommerceapp.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(username: String, password: String): Result<User> =
        userRepository.login(username, password)
}
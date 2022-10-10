package com.solodilov.ecommerceapp.domain.repository

import com.solodilov.ecommerceapp.domain.entity.Result
import com.solodilov.ecommerceapp.domain.entity.User

interface UserRepository {
    suspend fun login(name: String, password: String): Result<User>

    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
    suspend fun getUserId(): Int
    suspend fun saveUserId(userId: Int)
}
package com.solodilov.ecommerceapp.data.repository

import com.solodilov.ecommerceapp.data.datasource.LoginDataSource
import com.solodilov.ecommerceapp.data.model.AuthDto
import com.solodilov.ecommerceapp.data.preferences.Preferences
import com.solodilov.ecommerceapp.domain.entity.Result
import com.solodilov.ecommerceapp.domain.entity.User
import com.solodilov.ecommerceapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource,
    private val preferences: Preferences,
) : UserRepository {

    override suspend fun login(name: String, password: String): Result<User> =
        dataSource.login(AuthDto(username = name, password = password))

    override suspend fun getToken(): String? =
        preferences.getToken()

    override suspend fun saveToken(token: String) =
        preferences.setToken(token)

    override suspend fun getUserId(): Int =
        preferences.getUserId()

    override suspend fun saveUserId(userId: Int) =
        preferences.saveUserId(userId)

}
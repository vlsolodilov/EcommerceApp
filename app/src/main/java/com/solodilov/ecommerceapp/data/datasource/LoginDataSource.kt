package com.solodilov.ecommerceapp.data.datasource

import com.solodilov.ecommerceapp.data.model.AuthDto
import com.solodilov.ecommerceapp.domain.entity.Result
import com.solodilov.ecommerceapp.domain.entity.User

interface LoginDataSource {

    suspend fun login(auth: AuthDto): Result<User>
}
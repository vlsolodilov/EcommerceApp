package com.solodilov.ecommerceapp.data.datasource

import com.solodilov.ecommerceapp.data.model.AuthDto
import com.solodilov.ecommerceapp.data.network.DummyJsonApi
import com.solodilov.ecommerceapp.domain.entity.Result
import com.solodilov.ecommerceapp.domain.entity.User
import com.solodilov.ecommerceapp.domain.exeption.UserException
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val api: DummyJsonApi,
) : LoginDataSource {

    override suspend fun login(auth: AuthDto): Result<User> =
        try {
            val loggedInUserDto = api.login(auth)
            Result.Success(User(
                id = loggedInUserDto.id,
                token = loggedInUserDto.token
            ))
        } catch (e: HttpException) {
            Result.Error(UserException())
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
}
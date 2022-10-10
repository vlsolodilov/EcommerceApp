package com.solodilov.ecommerceapp.data.network

import com.solodilov.ecommerceapp.data.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptorImpl @Inject constructor(
    private val preferences: Preferences,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = preferences.getToken()

        return  chain.proceed(newRequestWithAccessToken(request, token))
    }

    private fun newRequestWithAccessToken(request: Request, token: String?): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
}
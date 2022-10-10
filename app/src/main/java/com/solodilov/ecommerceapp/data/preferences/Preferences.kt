package com.solodilov.ecommerceapp.data.preferences

interface Preferences {

    fun getToken(): String?
    fun setToken(token: String)
    fun getUserId(): Int
    fun saveUserId(userId: Int)
}
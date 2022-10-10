package com.solodilov.ecommerceapp.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject


class PreferencesImpl @Inject constructor(private val sharedPreferences: SharedPreferences)
    : Preferences {

    private companion object {
        const val TOKEN = "token"
        const val USER_ID = "user_id"
    }

    override fun getToken(): String? =
        sharedPreferences.getString(TOKEN, null)

    override fun setToken(token: String) =
        with (sharedPreferences.edit()) {
            putString(TOKEN, token)
            apply()
        }

    override fun getUserId(): Int =
        sharedPreferences.getInt(USER_ID, -1)

    override fun saveUserId(userId: Int) =
        with (sharedPreferences.edit()) {
            putInt(USER_ID, userId)
            apply()
        }

}
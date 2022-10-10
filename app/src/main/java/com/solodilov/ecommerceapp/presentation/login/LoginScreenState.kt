package com.solodilov.ecommerceapp.presentation.login

sealed class LoginScreenState {

    object Loading : LoginScreenState()
    object Content : LoginScreenState()
    object Error : LoginScreenState()

}
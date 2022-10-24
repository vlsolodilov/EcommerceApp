package com.solodilov.ecommerceapp.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.ecommerceapp.domain.entity.Result
import com.solodilov.ecommerceapp.domain.exeption.UserException
import com.solodilov.ecommerceapp.domain.usecase.GetTokenUseCase
import com.solodilov.ecommerceapp.domain.usecase.LoginUseCase
import com.solodilov.ecommerceapp.domain.usecase.SaveTokenUseCase
import com.solodilov.ecommerceapp.domain.usecase.SaveUserIdUseCase
import com.solodilov.ecommerceapp.extension.LiveEvent
import com.solodilov.ecommerceapp.extension.MutableLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
    val state: LiveData<LoginScreenState> = _state

    private val _loginSuccessEvent = MutableLiveEvent()
    val loginSuccessEvent: LiveEvent = _loginSuccessEvent

    private val _loginErrorEvent = MutableLiveEvent()
    val loginErrorEvent: LiveEvent = _loginErrorEvent

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        signInWithToken()
    }

    fun signInWithToken() {

        viewModelScope.launch {
            if (getTokenUseCase() != null) {
                _loginSuccessEvent()
            } else {
                _state.value = LoginScreenState.Content
            }
        }
    }

    fun signIn(username: String, password: String) {
        _state.value = LoginScreenState.Loading
        viewModelScope.launch(exceptionHandler) {
            when (val result = loginUseCase(username, password)) {
                is Result.Success ->  {
                    saveTokenUseCase(result.data.token)
                    saveUserIdUseCase(result.data.id)
                    _loginSuccessEvent()
                }
                is Result.Error -> {
                    Log.d(TAG, "signIn: error ${result.exception.message}")
                    if (result.exception is UserException) {
                        _state.value = LoginScreenState.Content
                        _loginErrorEvent()
                    } else {
                        _state.value = LoginScreenState.Error
                    }
                }
            }
        }
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _state.value = LoginScreenState.Error
    }

    companion object {
        const val TAG = "TAG"
    }
}
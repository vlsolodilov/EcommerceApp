package com.solodilov.ecommerceapp.presentation.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.ecommerceapp.domain.usecase.GetCartUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<CartScreenState>(CartScreenState.Loading)
    val state: LiveData<CartScreenState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        loadCartProductList()
    }

    fun loadCartProductList() {
        _state.value = CartScreenState.Loading
        Log.d(TAG, "loadCartProductList: ")
        viewModelScope.launch(exceptionHandler) {
            _state.postValue(CartScreenState.Content(content = getCartUseCase()))
        }

    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _state.value = CartScreenState.Error
    }

    companion object {
        const val TAG = "TAG"
    }
}
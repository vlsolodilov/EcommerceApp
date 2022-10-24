package com.solodilov.ecommerceapp.presentation.productdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.ecommerceapp.domain.usecase.GetProductUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<ProductDetailScreenState>(ProductDetailScreenState.Loading)
    val state: LiveData<ProductDetailScreenState> = _state

    private val _productCount = MutableLiveData<Int>(1)
    val productCount: LiveData<Int> = _productCount

    private var count = 1

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    fun loadProductDetail(id: Int) {
        _state.value = ProductDetailScreenState.Loading

        viewModelScope.launch(exceptionHandler) {
            val productDetail = getProductUseCase(id)
            _state.postValue(ProductDetailScreenState.Content(content = productDetail))
        }
    }

    fun increaseProductCount() {
        count++
        _productCount.value = count
    }

    fun decreaseProductCount() {
        if (count >= 2) count--
        _productCount.value = count
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _state.value = ProductDetailScreenState.Error
    }

    companion object {
        const val TAG = "TAG"
    }
}
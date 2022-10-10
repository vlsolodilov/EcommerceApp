package com.solodilov.ecommerceapp.presentation.productlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.ecommerceapp.domain.entity.Category
import com.solodilov.ecommerceapp.domain.usecase.GetProductListByCategoryUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val getProductListByCategoryUseCase: GetProductListByCategoryUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<ProductListScreenState>(ProductListScreenState.Loading)
    val state: LiveData<ProductListScreenState> = _state

    private val _category = MutableLiveData<Category>(Category.ALL)
    val category: LiveData<Category> = _category

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        loadProductList()
    }

    fun loadProductList() {
        _category.value?.let { currentCategory ->
            _state.value = ProductListScreenState.Loading
            Log.d(TAG, "loadProductList: ")
            viewModelScope.launch(exceptionHandler) {
                val productList = getProductListByCategoryUseCase(currentCategory)
                _state.postValue(ProductListScreenState.Content(content = productList))
            }
        }
    }

    fun setCategory(category: Category) {
        _category.value = category
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _state.value = ProductListScreenState.Error
    }

    companion object {
        const val TAG = "TAG"
    }
}
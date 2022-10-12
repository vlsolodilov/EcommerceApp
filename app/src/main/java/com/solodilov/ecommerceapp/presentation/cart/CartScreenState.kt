package com.solodilov.ecommerceapp.presentation.cart

import com.solodilov.ecommerceapp.domain.entity.Cart

sealed class CartScreenState {

    object Loading : CartScreenState()
    data class Content(val content: Cart?) : CartScreenState()
    object Error : CartScreenState()

}
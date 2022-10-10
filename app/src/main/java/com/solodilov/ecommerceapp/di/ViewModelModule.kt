package com.solodilov.ecommerceapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solodilov.ecommerceapp.presentation.ViewModelFactory
import com.solodilov.ecommerceapp.presentation.login.LoginViewModel
import com.solodilov.ecommerceapp.presentation.productlist.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    fun bindProductListViewModel(viewModel: ProductListViewModel): ViewModel
}
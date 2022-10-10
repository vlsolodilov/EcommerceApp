package com.solodilov.ecommerceapp.di

import com.solodilov.ecommerceapp.data.datasource.LoginDataSource
import com.solodilov.ecommerceapp.data.datasource.LoginDataSourceImpl
import com.solodilov.ecommerceapp.data.datasource.ProductDataSource
import com.solodilov.ecommerceapp.data.datasource.ProductDataSourceImpl
import com.solodilov.ecommerceapp.data.preferences.Preferences
import com.solodilov.ecommerceapp.data.preferences.PreferencesImpl
import com.solodilov.ecommerceapp.data.repository.ProductRepositoryImpl
import com.solodilov.ecommerceapp.data.repository.UserRepositoryImpl
import com.solodilov.ecommerceapp.domain.repository.ProductRepository
import com.solodilov.ecommerceapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

	@Singleton
	@Binds
	fun bindLoginDataSource(impl: LoginDataSourceImpl): LoginDataSource

	@Singleton
	@Binds
	fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

	@Singleton
	@Binds
	fun bindPreferences(impl: PreferencesImpl): Preferences

	@Singleton
	@Binds
	fun bindProductDataSource(impl: ProductDataSourceImpl): ProductDataSource

	@Singleton
	@Binds
	fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

}
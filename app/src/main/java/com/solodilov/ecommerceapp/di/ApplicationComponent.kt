package com.solodilov.ecommerceapp.di

import android.app.Application
import com.solodilov.ecommerceapp.presentation.MainActivity
import com.solodilov.ecommerceapp.presentation.login.LoginFragment
import com.solodilov.ecommerceapp.presentation.productlist.ProductListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
	DataModule::class,
	NetworkModule::class,
	ViewModelModule::class,
	PreferencesModule::class
])
interface ApplicationComponent {

	fun inject(activity: MainActivity)
	fun inject(fragment: LoginFragment)
	fun inject(fragment: ProductListFragment)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}
package com.solodilov.ecommerceapp.di

import com.solodilov.ecommerceapp.data.network.AuthInterceptorImpl
import com.solodilov.ecommerceapp.data.network.DummyJsonApi
import com.solodilov.ecommerceapp.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private companion object {

        const val BASE_URL = "https://dummyjson.com/auth/"
    }

    @Provides
    @DummyJsonUrl
    fun provideBaseUrl(): String =
        BASE_URL

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

	@Provides
    @Singleton
    fun provideAuthInterceptorImpl(
		preferences: Preferences,
	): AuthInterceptorImpl = AuthInterceptorImpl(preferences)

	@Provides
    @Singleton
    fun provideOkHttpClient(
		authInterceptor: AuthInterceptorImpl,
	): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @DummyJsonUrl baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideDummyJsonApi(
        retrofit: Retrofit,
    ): DummyJsonApi =
        retrofit.create(DummyJsonApi::class.java)
}
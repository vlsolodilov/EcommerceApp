package com.solodilov.ecommerceapp

import android.app.Application
import com.solodilov.ecommerceapp.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)
}
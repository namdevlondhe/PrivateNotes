package com.runo.notes

import android.app.Application
import com.runo.notes.di.AppComponent
import com.runo.notes.di.DaggerAppComponent

class MyNotesApplication : Application() {

    val appComponent : AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent () : AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}
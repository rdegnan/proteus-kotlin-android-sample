package io.netifi.proteus.kotlin.app

import android.app.Application

/**
 * Created by Maksym Ostroverkhov
 */
class App : Application() {
    private lateinit var model: MessagesModel

    override fun onCreate() {
        super.onCreate()
        model = MessagesModel()
        app = this
    }


    companion object {
        lateinit var app: App

        fun model() = app.model
    }
}
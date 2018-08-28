package io.netifi.proteus.kotlin.app

import android.app.Application
import io.netifi.proteus.kotlin.android.*
import io.netifi.proteus.kotlin.ext.KProteus
import io.netifi.proteus.kotlin.ext.builder.Group

/**
 * Created by Maksym Ostroverkhov
 */
class App : Application() {
    private lateinit var model: MessagesModel
    private lateinit var proteusManager: ProteusManager
    private val helloServices = Group("quickstart.services.helloservices")

    override fun onCreate() {
        super.onCreate()
        model = MessagesModel()
        app = this

        proteusManager =
                ProteusBuilder()
                        .proteus(ProteusTest) {
                            Configuration()
                                    .proteus {
                                        KProteus.builder()
                                                .credentials(HardCodedCredentials())
                                                .brokers(SingleBroker())
                                                .respondersDestination(helloServices)
                                                .transport(okHttpWebsocketTransport())
                                                .build()
                                    }
                                    .timeout(15)
                        }.build()
    }

    companion object {
        lateinit var app: App

        fun model() = app.model

        fun proteusManager() = app.proteusManager

        fun helloServices() = app.helloServices
    }

    object ProteusTest : ProteusRef
}
package io.netifi.proteus.kotlin.app

import android.app.Application
import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.JdkLoggerFactory
import io.rsocket.kotlin.Duration
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketFactory
import io.rsocket.transport.okhttp.client.OkhttpWebsocketClientTransport
import okhttp3.HttpUrl
import java.util.concurrent.TimeUnit

/**
 * Created by Maksym Ostroverkhov
 */
class App : Application() {
    private lateinit var model: MessagesModel
    private lateinit var rSocket: RSocket

    override fun onCreate() {
        super.onCreate()
        model = MessagesModel()
        app = this

        InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE);

        val url = HttpUrl.Builder()
                .scheme("http")
                .host("10.0.2.2")
                .port(9092)
                .build()
        rSocket = RSocketFactory
                .connect()
                .keepAlive {
                    it.keepAliveInterval(Duration.ofSeconds(10))
                    it.keepAliveMaxLifeTime(Duration.ofSeconds(100))
                }
                /*transport is pluggable */
                .transport(OkhttpWebsocketClientTransport.create(url))
                .start()
                .timeout(15, TimeUnit.SECONDS)
                .blockingGet()
    }

    companion object {
        lateinit var app: App

        fun model() = app.model

        fun rSocket() = app.rSocket
    }
}
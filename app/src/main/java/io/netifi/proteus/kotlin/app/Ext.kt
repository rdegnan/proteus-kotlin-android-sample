package io.netifi.proteus.kotlin.app

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.netifi.proteus.kotlin.ext.builder.BrokersOptions
import io.netifi.proteus.kotlin.ext.builder.CredentialOptions
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.rsocket.kotlin.transport.ClientTransport
import io.rsocket.transport.okhttp.client.OkhttpWebsocketClientTransport
import okhttp3.HttpUrl
import okhttp3.Request
import java.net.InetSocketAddress

/**
 * Created by Maksym Ostroverkhov
 */
internal fun okHttpWebsocketTransport(): (InetSocketAddress) -> ClientTransport =
        { addr ->
            val url = HttpUrl.Builder()
                    .scheme("http")
                    .host(addr.hostName)
                    .port(addr.port).build()
            val request = Request.Builder().url(url).build()

            OkhttpWebsocketClientTransport.create(request)
        }

internal class HardCodedCredentials : (CredentialOptions) -> Unit {
    override fun invoke(opts: CredentialOptions) {
        opts.accessKey(9007199254740991)
                .accessToken("kTBDVtfRBO4tHOnZzSyY5ym2kfY=")
    }
}

internal class SingleBroker : (BrokersOptions) -> Unit {
    override fun invoke(opts: BrokersOptions) {
        opts.address("192.168.1.104", 8101)
    }
}

internal fun click(view: View): Flowable<Any> =
        RxView.clicks(view).toFlowable(BackpressureStrategy.LATEST)

internal operator fun CompositeDisposable.plusAssign(d: Disposable) {
    this.add(d)
}

@SuppressLint("SetTextI18n")
internal fun bind(flow: Flowable<ResponseItem>, v: TextView): Disposable =
        flow.subscribe { v.text = "${it.interaction}. Total messages: ${it.msg} ${it.counter}" }

@SuppressLint("SetTextI18n")
internal fun bindError(flow: Flowable<Throwable>, v: TextView): Disposable =
        flow.subscribe { v.text = "Error: ${it.message}" }

data class ResponseItem(val interaction:String, val msg: String, val counter: Int)


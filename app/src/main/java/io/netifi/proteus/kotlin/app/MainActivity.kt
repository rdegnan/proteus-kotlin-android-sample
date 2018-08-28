package io.netifi.proteus.kotlin.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.netifi.proteus.kotlin.android.ProteusManager
import io.netifi.proteus.kotlin.app.App.ProteusTest
import io.netifi.proteus.kotlin.ext.builder.Group
import io.netifi.proteus.kotlin.sample.protobuf.InteractionsService
import io.netifi.proteus.kotlin.sample.protobuf.InteractionsServiceClient
import io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest
import io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse
import io.netty.buffer.ByteBuf
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.reactivestreams.Publisher

class MainActivity : AppCompatActivity() {
    private lateinit var proteusManager: ProteusManager
    private lateinit var client: InteractionsServiceClient
    private lateinit var helloServices: Group

    private lateinit var d: CompositeDisposable
    private lateinit var model: MessagesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        model = App.model()
        proteusManager = App.proteusManager()
        helloServices = App.helloServices()
    }

    override fun onStart() {
        super.onStart()
        start()
    }

    override fun onStop() {
        stop()
        super.onStop()
    }

    private fun start() {
        d = CompositeDisposable()

        val msg = SimpleRequest
                .newBuilder()
                .setRequestMessage("msg").build()

        d += click(request_response_button)
                .flatMapSingle {
                    client.requestResponse(msg)
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(
                        { model.receiveRequestResponse(it) },
                        { model.receiveError(it) })

        d += click(client_stream_button)
                .flatMapSingle {
                    client.clientStream(Flowable.just(msg, msg))
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(
                        { model.receiveClientStream(it) },
                        { model.receiveError(it) })

        d += click(server_stream_button)
                .flatMap {
                    client.serverStream(msg)
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(
                        { model.receiveServerStream(it) },
                        { model.receiveError(it) })

        d += click(channel_button).flatMap {
            client.channel(
                    Flowable.range(1, 7).map { msg })
                    .observeOn(AndroidSchedulers.mainThread())
        }
                .subscribe(
                        { model.receiveChannel(it) },
                        { model.receiveError(it) })

        d += bind(model.channelMessages(), channel_result_view)
        d += bind(model.requestResponseMessages(), req_rep_result_view)
        d += bind(model.clientSreamMessages(), client_stream_result_view)
        d += bind(model.serverStreamMessages(), server_stream_result_view)
        d += bindError(model.errors(), server_stream_result_view)

        val proteus = proteusManager
                .obtain(ProteusTest) {
                    it.responder(ServiceHandler())
                }
        client = proteus.requester(helloServices)
    }

    private fun stop() {
        d.dispose()
        proteusManager.release(ProteusTest)
    }

    private class ServiceHandler : InteractionsService {
        override fun clientStream(messages: Publisher<SimpleRequest>?, metadata: ByteBuf?)
                : Single<SimpleResponse> =
                Flowable.fromPublisher(messages)
                        .lastOrError()
                        .map {
                            SimpleResponse.newBuilder()
                                    .setResponseMessage(it.requestMessage)
                                    .build()
                        }

        override fun channel(messages: Publisher<SimpleRequest>,
                             metadata: ByteBuf?): Flowable<SimpleResponse> =
                Flowable.fromPublisher(messages).map { response(it) }

        override fun requestResponse(message: SimpleRequest,
                                     metadata: ByteBuf?): Single<SimpleResponse> =
                Single.just(response(message))

        override fun serverStream(message: SimpleRequest,
                                  metadata: ByteBuf?): Flowable<SimpleResponse> {
            val response = response(message)
            return Flowable.just(response, response, response)
        }

        private fun response(request: SimpleRequest) =
                SimpleResponse
                        .newBuilder()
                        .setResponseMessage(request.requestMessage).build()

    }
}

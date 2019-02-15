package io.netifi.proteus.kotlin.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.protobuf.Value
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.rsocket.kotlin.RSocket
import io.rsocket.rpc.echo.EchoServiceClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rSocket: RSocket
    private lateinit var client: EchoServiceClient

    private lateinit var d: CompositeDisposable
    private lateinit var model: MessagesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        model = App.model()
        rSocket = App.rSocket()
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

        val msg = Value
                .newBuilder()
                .setStringValue("msg").build()

        d += click(request_response_button)
                .flatMapSingle {
                    client.requestResponse(msg)
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(
                        { model.receiveRequestResponse(it) },
                        { model.receiveError(it) })

        // TODO
        /*d += click(client_stream_button)
                .flatMapSingle {
                    client.clientStream(Flowable.just(msg, msg))
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(
                        { model.receiveClientStream(it) },
                        { model.receiveError(it) })*/

        d += click(server_stream_button)
                .flatMap {
                    client.requestStream(msg)
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(
                        { model.receiveServerStream(it) },
                        { model.receiveError(it) })

        d += click(channel_button).flatMap {
            client.requestChannel(
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

        client = EchoServiceClient(rSocket)
    }

    private fun stop() {
        d.dispose()
        rSocket.close()
    }
}

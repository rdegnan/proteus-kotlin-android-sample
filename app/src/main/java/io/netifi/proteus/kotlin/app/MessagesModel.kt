package io.netifi.proteus.kotlin.app

import com.google.protobuf.Value
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

/**
 * Created by Maksym Ostroverkhov
 */
class MessagesModel {

    private val reqResp = Counting("Request-Response")
    private val serverStream = Counting("Server-Stream")
    private val clientStream = Counting("Client-Stream")
    private val channelStream = Counting("Channel")
    private val error = PublishProcessor.create<Throwable>()

    fun receiveRequestResponse(resp: Value) {
        reqResp.count(resp)
    }

    fun receiveClientStream(resp: Value) {
        clientStream.count(resp)
    }

    fun receiveServerStream(resp: Value) {
        serverStream.count(resp)
    }

    fun receiveChannel(resp: Value) {
        channelStream.count(resp)
    }

    fun receiveError(err: Throwable) {
        err.printStackTrace()
        error.onNext(err)
    }

    fun channelMessages(): Flowable<ResponseItem> {
        return channelStream.stream()
    }

    fun requestResponseMessages(): Flowable<ResponseItem> {
        return reqResp.stream()
    }

    fun serverStreamMessages(): Flowable<ResponseItem> {
        return serverStream.stream()
    }

    fun clientSreamMessages(): Flowable<ResponseItem> {
        return clientStream.stream()
    }

    fun errors(): Flowable<Throwable> {
        return error
    }

    private class Counting(private val interaction: String) {
        private val stream = BehaviorProcessor.create<ResponseItem>()
        private var counter = 0
        fun count(msg: Value) {
            counter++
            stream.onNext(ResponseItem(interaction, msg.stringValue, counter))
        }

        fun stream(): Flowable<ResponseItem> = stream
    }
}


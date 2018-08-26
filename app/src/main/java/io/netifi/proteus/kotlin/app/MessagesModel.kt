package io.netifi.proteus.kotlin.app

import io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse
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

    fun receiveRequestResponse(resp: SimpleResponse) {
        reqResp.count(resp)
    }

    fun receiveClientStream(resp: SimpleResponse) {
        clientStream.count(resp)
    }

    fun receiveServerStream(resp: SimpleResponse) {
        serverStream.count(resp)
    }

    fun receiveChannel(resp: SimpleResponse) {
        channelStream.count(resp)
    }

    fun receiveError(err: Throwable) {
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
        fun count(msg: SimpleResponse) {
            counter++
            stream.onNext(ResponseItem(interaction, msg.responseMessage, counter))
        }

        fun stream(): Flowable<ResponseItem> = stream
    }
}


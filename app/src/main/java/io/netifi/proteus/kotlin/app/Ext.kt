package io.netifi.proteus.kotlin.app

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Maksym Ostroverkhov
 */
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


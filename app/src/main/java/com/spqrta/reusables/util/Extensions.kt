package com.spqrta.reusables.util

import android.util.Size
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit


fun String.toBase64(): String {
    return Base64Utils.encode(this)
}

fun View.hide() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.setInvisibleState(value: Boolean) {
    visibility = if(value) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

fun View.setGoneState(value: Boolean) {
    visibility = if(value) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

fun <T> Single<T>.delayExecution(milliseconds: Int): Single<T> {
    return delay(milliseconds.toLong(), TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
}

fun <T> Single<T>.attachProgressbar(progressBar: View): Single<T> {
    return doOnSubscribe {
        progressBar.show()
    }. doOnEvent { _, _ ->
        progressBar.hide()
    }
}

fun <T> Single<T>.delayExecution(milliseconds: Long): Single<T> {
    return delay(milliseconds, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
}

fun TextView.textString(): String {
    return text.toString()
}

fun String.parseDate(dateTimeFormatter: DateTimeFormatter): LocalDate {
    return LocalDate.parse(this, dateTimeFormatter)
}

fun <T : Any?> MutableLiveData<T>.initWith(initialValue: T) = apply { setValue(initialValue) }

fun <T : Any?> MutableLiveData<T>.init(initializer: () -> T) = apply { setValue(initializer.invoke()) }

fun String?.nullIfEmpty(): String? = if (this.isNullOrEmpty()) {
    null
} else {
    this
}

fun ZonedDateTime.toLocalDateTimeOnThisZone(): LocalDateTime {
    val zone = ZoneId.systemDefault()
    val zoned = this.withZoneSameInstant(zone)
    return zoned.toLocalDateTime()
}

fun Size.toStringHw(): String {
    return "${height}x$width"
}

fun Size.toStringWh(): String {
    return "${width}x$height"
}

fun <T> T?.replaceIfNull(obj: T): T {
    return this ?: obj
}


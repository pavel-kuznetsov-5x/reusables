package com.spqrta.reusables.util

import android.preference.PreferenceManager
import com.spqrta.reusables.CustomApplication
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

abstract class Setting<T> {
    abstract val key: String
    val prefs = PreferenceManager.getDefaultSharedPreferences(CustomApplication.context)

    abstract fun load(): T
    abstract fun save(value: T)
}

abstract class StringSetting : Setting<String?>() {
    override fun load(): String? = prefs.getString(key, null)

    override fun save(value: String?) {
        prefs.edit().putString(key, value).apply()
    }
}

abstract class NonNullBooleanSetting : Setting<Boolean>() {
    override fun load(): Boolean = prefs.getBoolean(key, false)

    override fun save(value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }
}

abstract class StringListSetting : Setting<List<String>?>() {
    protected open val separator = "{;#.]"

    override fun load(): List<String>? = prefs.getString(key, null)?.split(separator)

    override fun save(value: List<String>?) {
        prefs.edit().putString(key, value?.joinToString(separator)).apply()
    }
}

abstract class StringSetSetting : Setting<Set<String>?>() {
    protected open val separator = "{;#.]"

    override fun load(): Set<String>? = prefs.getString(key, null)?.split(separator)?.toSet()

    override fun save(value: Set<String>?) {
        prefs.edit().putString(key, value?.joinToString(separator)).apply()
    }
}

abstract class ZonedDateTimeSetting : Setting<ZonedDateTime?>() {
    protected open val separator = "{;#.]"

    override fun load(): ZonedDateTime? = prefs.getString(key, null)?.let {
        ZonedDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
    }

    override fun save(value: ZonedDateTime?) {
        prefs.edit().putString(key, value?.format(DateTimeFormatter.ISO_DATE_TIME)).apply()
    }
}
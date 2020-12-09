package com.spqrta.reusables.utility.utils

fun String?.emptyIfNull(): String {
    return this ?: ""
}
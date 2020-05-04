package com.spqrta.reusables.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

object Intents {
    @SuppressLint("MissingPermission")
    fun initiateCall(context: Context, number: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"));
        context.startActivity(intent)
    }
}
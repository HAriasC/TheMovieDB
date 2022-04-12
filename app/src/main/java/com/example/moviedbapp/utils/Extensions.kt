package com.example.moviedbapp.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

const val API_KEY = "9af907de7aa7814a33a6a8a77c6939ab"

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun TextView.setTextSpanStart(value: String, index: Int) {
    if (!TextUtils.isEmpty(value)) {

        if (index > 0 && index < value.length) {
            val sb = SpannableStringBuilder(value)
            sb.setSpan(StyleSpan(Typeface.BOLD), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            text = SpannableString(sb)
        } else {
            text = value
        }
    }
}
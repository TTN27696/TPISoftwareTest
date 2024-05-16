package com.example.tpisoftwaretest.utility

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tpisoftwaretest.R

fun TextView.setTextHighLight(textHighLight: String, onClickText: () -> Unit) {
    val highlight: Spannable?
    highlight = SpannableString(textHighLight)
    setClickableSpan(highlight, context = context, onClickText = onClickText)
    highlight.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, R.color.link)),
        0, highlight.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    append(highlight)
    movementMethod = LinkMovementMethod.getInstance()
}

private fun setClickableSpan(spannable: Spannable, context: Context, onClickText: () -> Unit) {
    val clickableLink: ClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            widget.postInvalidate()
            onClickText.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            val linkColor = ContextCompat.getColor(context, R.color.red)
            ds.color = linkColor
            ds.isUnderlineText = false
        }
    }
    spannable.setSpan(clickableLink, 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}
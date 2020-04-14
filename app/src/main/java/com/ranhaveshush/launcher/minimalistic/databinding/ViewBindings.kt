package com.ranhaveshush.launcher.minimalistic.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun isGone(view: View, gone: Boolean) {
    view.visibility = if (gone) View.GONE else View.VISIBLE
}

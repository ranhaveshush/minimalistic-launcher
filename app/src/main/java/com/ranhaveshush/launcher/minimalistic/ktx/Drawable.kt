package com.ranhaveshush.launcher.minimalistic.ktx

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable

fun BitmapDrawable.toBytes(format: Bitmap.CompressFormat): ByteArray = bitmap.toBytes(format)
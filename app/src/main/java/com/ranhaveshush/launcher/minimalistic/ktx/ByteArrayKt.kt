package com.ranhaveshush.launcher.minimalistic.ktx

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

fun ByteArray.toBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, size)

fun ByteArray.toDrawable(): Drawable = BitmapDrawable(toBitmap())
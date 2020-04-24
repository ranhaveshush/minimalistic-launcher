package com.ranhaveshush.launcher.minimalistic.ktx

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.toBytes(format: Bitmap.CompressFormat): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(format, 100, stream)
    return stream.toByteArray()
}
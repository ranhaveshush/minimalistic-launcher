package com.ranhaveshush.launcher.minimalistic.db

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.room.TypeConverter
import com.ranhaveshush.launcher.minimalistic.ktx.toBytes
import com.ranhaveshush.launcher.minimalistic.ktx.toDrawable

class Converters {
    @TypeConverter
    fun drawableToByteArray(drawable: Drawable?): ByteArray? = (drawable as BitmapDrawable).toBytes(Bitmap.CompressFormat.PNG)

    @TypeConverter
    fun drawableFromByteArray(bytes: ByteArray?): Drawable? = bytes?.toDrawable()
}


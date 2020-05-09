package com.ranhaveshush.launcher.minimalistic.data

/**
 * Data transformer, transforms input data [T] to output data [R].
 */
interface DataTransformer<T, R> {
    fun transform(data: T): R
}

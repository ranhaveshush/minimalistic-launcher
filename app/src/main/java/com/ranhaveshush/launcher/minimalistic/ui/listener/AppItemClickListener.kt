package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.AppItem

/**
 * A click listener or an [AppItem].
 */
interface AppItemClickListener {
    fun onAppClick(appItem: AppItem)
}
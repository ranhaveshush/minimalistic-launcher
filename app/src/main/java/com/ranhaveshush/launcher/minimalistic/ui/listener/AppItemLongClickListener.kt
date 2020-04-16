package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.AppItem

/**
 * A long click listener for an [AppItem].
 */
interface AppItemLongClickListener {
    fun onAppLongClick(appItem: AppItem): Boolean
}
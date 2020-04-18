package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem

/**
 * A long click listener for an [DrawerAppItem].
 */
interface DrawerAppItemLongClickListener {
    fun onAppLongClick(appItem: DrawerAppItem): Boolean
}
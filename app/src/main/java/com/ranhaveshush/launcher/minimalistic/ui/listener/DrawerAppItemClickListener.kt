package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem

/**
 * A click listener or an [DrawerAppItem].
 */
interface DrawerAppItemClickListener {
    fun onAppClick(appItem: DrawerAppItem)
}
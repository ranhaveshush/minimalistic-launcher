package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.DrawerApp

/**
 * A long click listener for an [DrawerApp].
 */
interface DrawerAppItemLongClickListener {
    fun onAppLongClick(app: DrawerApp): Boolean
}

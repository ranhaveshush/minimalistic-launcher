package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.HomeApp

/**
 * A long click listener for an [HomeApp].
 */
interface HomeAppItemLongClickListener {
    fun onAppLongClick(app: HomeApp): Boolean
}

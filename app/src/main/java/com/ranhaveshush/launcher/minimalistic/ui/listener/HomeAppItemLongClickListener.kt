package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem

/**
 * A long click listener for an [HomeAppItem].
 */
interface HomeAppItemLongClickListener {
    fun onAppLongClick(appItem: HomeAppItem): Boolean
}
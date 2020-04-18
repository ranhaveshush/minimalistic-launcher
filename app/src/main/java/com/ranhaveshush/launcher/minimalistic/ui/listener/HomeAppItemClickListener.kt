package com.ranhaveshush.launcher.minimalistic.ui.listener

import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem

/**
 * A click listener or an [HomeAppItem].
 */
interface HomeAppItemClickListener {
    fun onAppClick(appItem: HomeAppItem)
}
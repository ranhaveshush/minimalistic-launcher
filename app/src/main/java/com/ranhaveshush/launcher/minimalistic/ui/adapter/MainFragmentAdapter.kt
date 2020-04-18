package com.ranhaveshush.launcher.minimalistic.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * This main fragment adapter holds the top level screens
 * (e.g. [HomeFragment][com.ranhaveshush.launcher.minimalistic.ui.fragment.HomeFragment],
 * [AppDrawerFragment][com.ranhaveshush.launcher.minimalistic.ui.fragment.AppDrawerFragment]).
 */
class MainFragmentAdapter(
    fragmentActivity: FragmentActivity,
    private val pages: List<Fragment> = emptyList()
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment = pages[position]
}
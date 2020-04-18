package com.ranhaveshush.launcher.minimalistic.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranhaveshush.launcher.minimalistic.R
import com.ranhaveshush.launcher.minimalistic.ui.adapter.MainFragmentAdapter
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * This main fragment represents a container for the top level screens
 * (e.g. home and app drawer screens).
 */
class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pages = listOf(
            HomeFragment(),
            AppDrawerFragment()
        )

        viewPager.adapter = MainFragmentAdapter(requireActivity(), pages)
    }
}

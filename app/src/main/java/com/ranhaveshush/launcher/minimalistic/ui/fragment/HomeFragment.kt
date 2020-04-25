package com.ranhaveshush.launcher.minimalistic.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ranhaveshush.launcher.minimalistic.R
import com.ranhaveshush.launcher.minimalistic.databinding.FragmentHomeBinding
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.ui.adapter.HomeAppsAdapter
import com.ranhaveshush.launcher.minimalistic.ui.listener.HomeAppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.HomeAppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import com.ranhaveshush.launcher.minimalistic.viewmodel.HomeViewModel
import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource

/**
 * This home fragment represents the home screen,
 * a container for the favorite fast access apps.
 */
class HomeFragment : Fragment(R.layout.fragment_home), HomeAppItemClickListener, HomeAppItemLongClickListener {
    private val viewModel: HomeViewModel by viewModels {
        InjectorUtils().provideHomeViewModelFactory(packageManager)
    }

    private val appsAdapter = HomeAppsAdapter(this, this)

    private val appsLauncher = AppsLauncher()

    private val settingsLauncher = SettingsLauncher()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.recyclerViewApps.adapter = appsAdapter

        viewModel.apps.observe(viewLifecycleOwner, Observer {
            if (it.state.status == Resource.Status.SUCCESS) {
                appsAdapter.submitList(it.data)
            }
        })

        return binding.root
    }

    override fun onAppClick(appItem: HomeAppItem) = appsLauncher.launch(application, appItem.packageName)

    override fun onAppLongClick(appItem: HomeAppItem) = settingsLauncher.launchAppDetails(application, appItem.packageName)
}

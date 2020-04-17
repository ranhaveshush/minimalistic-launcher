package com.ranhaveshush.launcher.minimalistic.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranhaveshush.launcher.minimalistic.R
import com.ranhaveshush.launcher.minimalistic.databinding.FragmentAppDrawerBinding
import com.ranhaveshush.launcher.minimalistic.launcher.AppsLauncher
import com.ranhaveshush.launcher.minimalistic.launcher.SettingsLauncher
import com.ranhaveshush.launcher.minimalistic.ui.adapter.AppsAdapter
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import com.ranhaveshush.launcher.minimalistic.viewmodel.AppDrawerViewModel
import com.ranhaveshush.launcher.minimalistic.vo.AppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource.Status

class AppDrawerFragment : Fragment(R.layout.fragment_app_drawer), AppItemClickListener, AppItemLongClickListener {
    private val viewModel: AppDrawerViewModel by viewModels {
        InjectorUtils().provideAppDrawerViewModelFactory(requireContext().packageManager)
    }

    private val appsLauncher = AppsLauncher()

    private val settingsLauncher = SettingsLauncher()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAppDrawerBinding.inflate(layoutInflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.recyclerViewApps.adapter = AppsAdapter(this, this)
        binding.recyclerViewApps.layoutManager = LinearLayoutManager(context).apply {
            stackFromEnd = true
        }

        viewModel.apps.observe(viewLifecycleOwner, Observer {
            if (it.state.status == Status.SUCCESS) {
                (binding.recyclerViewApps.adapter as AppsAdapter).submitList(it.data)
            }
        })

        return binding.root
    }

    override fun onAppClick(appItem: AppItem) {
        appsLauncher.launch(requireActivity().application, appItem.packageName)
    }

    override fun onAppLongClick(appItem: AppItem): Boolean {
        return settingsLauncher.launchAppDetails(requireActivity().application, appItem.packageName)
    }
}

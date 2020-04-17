package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemHomeAppBinding
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.vo.AppItem

class HomeAppsAdapter(
    private val appItemClickListener: AppItemClickListener,
    private val appItemLongClickListener: AppItemLongClickListener
) : ListAdapter<AppItem, HomeAppViewHolder>(AppItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAppViewHolder {
        val binding = ListItemHomeAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.root.setOnClickListener {
            appItemClickListener.onAppClick(it.tag as AppItem)
        }
        binding.root.setOnLongClickListener {
            appItemLongClickListener.onAppLongClick(it.tag as AppItem)
        }

        return HomeAppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeAppViewHolder(private val binding: ListItemHomeAppBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AppItem) {
        binding.root.tag = item
        binding.apply {
            appItem = item
            executePendingBindings()
        }
    }
}
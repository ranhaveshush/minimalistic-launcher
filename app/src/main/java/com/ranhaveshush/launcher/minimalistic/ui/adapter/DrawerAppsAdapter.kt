package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemDrawerAppBinding
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.vo.AppItem

class DrawerAppsAdapter(
    private val appItemClickListener: AppItemClickListener,
    private val appItemLongClickListener: AppItemLongClickListener
) : ListAdapter<AppItem, DrawerAppViewHolder>(AppItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerAppViewHolder {
        val binding = ListItemDrawerAppBinding.inflate(
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

        return DrawerAppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrawerAppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DrawerAppViewHolder(private val binding: ListItemDrawerAppBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AppItem) {
        binding.root.tag = item
        binding.apply {
            appItem = item
            executePendingBindings()
        }
    }
}

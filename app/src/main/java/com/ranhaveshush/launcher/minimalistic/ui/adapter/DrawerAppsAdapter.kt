package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemDrawerAppBinding
import com.ranhaveshush.launcher.minimalistic.ui.listener.DrawerAppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.DrawerAppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem

class DrawerAppsAdapter(
    private val itemClickListener: DrawerAppItemClickListener,
    private val itemLongClickListener: DrawerAppItemLongClickListener
) : ListAdapter<DrawerAppItem, DrawerAppViewHolder>(DrawerAppItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerAppViewHolder {
        val binding = ListItemDrawerAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.root.setOnClickListener {
            itemClickListener.onAppClick(it.tag as DrawerAppItem)
        }
        binding.root.setOnLongClickListener {
            itemLongClickListener.onAppLongClick(it.tag as DrawerAppItem)
        }

        return DrawerAppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrawerAppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DrawerAppViewHolder(private val binding: ListItemDrawerAppBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DrawerAppItem) {
        binding.apply {
            root.tag = item
            app = item
            executePendingBindings()
        }
    }
}

class DrawerAppItemDiffCallback : DiffUtil.ItemCallback<DrawerAppItem>() {

    override fun areItemsTheSame(oldItem: DrawerAppItem, newItem: DrawerAppItem) = oldItem.label == newItem.label

    override fun areContentsTheSame(oldItem: DrawerAppItem, newItem: DrawerAppItem) = oldItem.packageName == newItem.packageName
            && oldItem.activityName == newItem.activityName
}

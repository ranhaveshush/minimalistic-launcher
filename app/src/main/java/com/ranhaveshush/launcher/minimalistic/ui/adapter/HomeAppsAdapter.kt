package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemHomeAppBinding
import com.ranhaveshush.launcher.minimalistic.ui.listener.HomeAppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.HomeAppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.vo.HomeAppItem

class HomeAppsAdapter(
    private val itemClickListener: HomeAppItemClickListener,
    private val itemLongClickListener: HomeAppItemLongClickListener
) : ListAdapter<HomeAppItem, HomeAppViewHolder>(HomeAppItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAppViewHolder {
        val binding = ListItemHomeAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.root.setOnClickListener {
            itemClickListener.onAppClick(it.tag as HomeAppItem)
        }
        binding.root.setOnLongClickListener {
            itemLongClickListener.onAppLongClick(it.tag as HomeAppItem)
        }

        return HomeAppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeAppViewHolder(private val binding: ListItemHomeAppBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeAppItem) {
        binding.apply {
            root.tag = item
            app = item
            executePendingBindings()
        }
    }
}

class HomeAppItemDiffCallback : DiffUtil.ItemCallback<HomeAppItem>() {

    override fun areItemsTheSame(oldItem: HomeAppItem, newItem: HomeAppItem) = oldItem.label == newItem.label

    override fun areContentsTheSame(oldItem: HomeAppItem, newItem: HomeAppItem) = oldItem.packageName == newItem.packageName
            && oldItem.activityName == newItem.activityName
}
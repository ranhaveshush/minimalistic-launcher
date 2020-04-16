package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemAppBinding
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.AppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.vo.AppItem

class AppsAdapter(
    private val appItemClickListener: AppItemClickListener,
    private val appItemLongClickListener: AppItemLongClickListener
) : ListAdapter<AppItem, AppViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = ListItemAppBinding.inflate(
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

        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AppViewHolder(private val binding: ListItemAppBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AppItem) {
        binding.root.tag = item
        binding.apply {
            appItem = item
            executePendingBindings()
        }
    }
}

class AppDiffCallback : DiffUtil.ItemCallback<AppItem>() {

    override fun areItemsTheSame(oldItem: AppItem, newItem: AppItem) = oldItem.packageName == newItem.packageName

    override fun areContentsTheSame(oldItem: AppItem, newItem: AppItem) = oldItem == newItem
}

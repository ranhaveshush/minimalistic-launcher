package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemNotificationBinding
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem

class NotificationsAdapter : ListAdapter<NotificationItem, NotificationViewHolder>(NotificationDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ListItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotificationViewHolder(private val binding: ListItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NotificationItem) {
        binding.apply {
            notification = item
            executePendingBindings()
        }
    }
}

class NotificationDiffCallback : DiffUtil.ItemCallback<NotificationItem>() {
    override fun areContentsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean = oldItem == newItem
}

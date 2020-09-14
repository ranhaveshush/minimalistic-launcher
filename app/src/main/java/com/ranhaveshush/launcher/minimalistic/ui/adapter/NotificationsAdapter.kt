package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.R
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemNotificationBinding
import com.ranhaveshush.launcher.minimalistic.ui.item.NotificationItem
import com.ranhaveshush.launcher.minimalistic.ui.item.NotificationItemDiffCallback
import com.ranhaveshush.launcher.minimalistic.ui.listener.ClearAllNotificationsClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.NotificationItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.recyclerview.SwipeableViewHolder
import com.ranhaveshush.launcher.minimalistic.vo.Notification

class NotificationsAdapter(
    private val itemClickListener: NotificationItemClickListener,
    private val clearAllNotificationsClickListener: ClearAllNotificationsClickListener
) : ListAdapter<NotificationItem, NotificationViewHolder>(NotificationItemDiffCallback()) {
    override fun getItemViewType(position: Int): Int =
        getItem(position).type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        when (NotificationItem.Type.values()[viewType]) {
            NotificationItem.Type.HEADER -> NotificationHeaderViewHolder.create(
                parent,
                clearAllNotificationsClickListener
            )
            NotificationItem.Type.ENTRY -> NotificationEntryViewHolder.create(parent, itemClickListener)
        }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        if (holder is NotificationEntryViewHolder) {
            val item = getItem(position).value as Notification
            holder.bind(item)
        }
    }

    fun getNotificationItem(position: Int): Notification? =
        getItem(position).value as Notification
}

sealed class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SwipeableViewHolder

class NotificationHeaderViewHolder(
    headerView: View
) : NotificationViewHolder(headerView) {
    override val isSwipeable: Boolean = false

    companion object {
        fun create(
            parent: ViewGroup,
            clearAllNotificationsClickListener: ClearAllNotificationsClickListener
        ): NotificationHeaderViewHolder {
            val headerView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_header_notification,
                parent,
                false
            )

            val clearAllButton: View = headerView.findViewById(R.id.button_clearAll)
            clearAllButton.setOnClickListener {
                clearAllNotificationsClickListener.onClearAllClick()
            }

            return NotificationHeaderViewHolder(headerView)
        }
    }
}

class NotificationEntryViewHolder(
    private val binding: ListItemNotificationBinding
) : NotificationViewHolder(binding.root) {
    override val isSwipeable: Boolean = true

    fun bind(item: Notification) {
        binding.apply {
            root.tag = item
            notification = item
            executePendingBindings()
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClickListener: NotificationItemClickListener
        ): NotificationEntryViewHolder {
            val binding = ListItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            binding.root.setOnClickListener {
                itemClickListener.onNotificationClick(it.tag as Notification)
            }

            return NotificationEntryViewHolder(binding)
        }
    }
}

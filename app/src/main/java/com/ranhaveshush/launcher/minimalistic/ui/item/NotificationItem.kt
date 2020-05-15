package com.ranhaveshush.launcher.minimalistic.ui.item

import androidx.recyclerview.widget.DiffUtil
import com.ranhaveshush.launcher.minimalistic.ui.item.NotificationItem.Type
import com.ranhaveshush.launcher.minimalistic.vo.Notification
import com.ranhaveshush.launcher.minimalistic.vo.NotificationHeader
import com.ranhaveshush.launcher.minimalistic.vo.ValueObject

/**
 * A notification item of a given [Type].
 */
data class NotificationItem(
    val type: Type,
    val value: ValueObject
) {
    enum class Type {
        HEADER,
        ENTRY
    }
}

class NotificationItemDiffCallback : DiffUtil.ItemCallback<NotificationItem>() {
    private val headerItemDiffCallback = HeaderItemDiffCallback()
    private val entryItemDiffCallback = EntryItemDiffCallback()

    override fun areContentsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean {
        if (oldItem.type != newItem.type) return false

        return when (oldItem.type) {
            Type.HEADER -> headerItemDiffCallback.areContentsTheSame(
                oldItem.value as NotificationHeader,
                newItem.value as NotificationHeader
            )
            Type.ENTRY -> entryItemDiffCallback.areContentsTheSame(
                oldItem.value as Notification,
                newItem.value as Notification
            )
        }
    }

    override fun areItemsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean {
        if (oldItem.type != newItem.type) return false

        return when (oldItem.type) {
            Type.HEADER -> headerItemDiffCallback.areItemsTheSame(
                oldItem.value as NotificationHeader,
                newItem.value as NotificationHeader
            )
            Type.ENTRY -> entryItemDiffCallback.areItemsTheSame(
                oldItem.value as Notification,
                newItem.value as Notification
            )
        }
    }

    private class HeaderItemDiffCallback : DiffUtil.ItemCallback<NotificationHeader>() {
        override fun areItemsTheSame(oldItem: NotificationHeader, newItem: NotificationHeader): Boolean = true

        override fun areContentsTheSame(oldItem: NotificationHeader, newItem: NotificationHeader): Boolean = true
    }

    private class EntryItemDiffCallback : DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean = oldItem.appLabel == newItem.appLabel
                && oldItem.postTime == newItem.postTime
                && oldItem.text == newItem.text
                && oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean = oldItem.key == newItem.key
    }
}

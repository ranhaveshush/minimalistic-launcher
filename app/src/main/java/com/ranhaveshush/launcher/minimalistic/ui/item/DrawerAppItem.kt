package com.ranhaveshush.launcher.minimalistic.ui.item

import androidx.recyclerview.widget.DiffUtil
import com.ranhaveshush.launcher.minimalistic.ui.item.DrawerAppItem.Type
import com.ranhaveshush.launcher.minimalistic.vo.DrawerApp
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppHeader
import com.ranhaveshush.launcher.minimalistic.vo.ValueObject

/**
 * A app drawer item of a given [Type].
 */
data class DrawerAppItem(
    val type: Type,
    val value: ValueObject
) {
    enum class Type {
        HEADER,
        ENTRY
    }
}

class DrawerAppItemDiffCallback : DiffUtil.ItemCallback<DrawerAppItem>() {
    private val headerItemDiffCallback = HeaderItemDiffCallback()
    private val entryItemDiffCallback = EntryItemDiffCallback()

    override fun areContentsTheSame(oldItem: DrawerAppItem, newItem: DrawerAppItem): Boolean {
        if (oldItem.type != newItem.type) return false

        return when (oldItem.type) {
            Type.HEADER -> headerItemDiffCallback.areContentsTheSame(
                oldItem.value as DrawerAppHeader,
                newItem.value as DrawerAppHeader
            )
            Type.ENTRY -> entryItemDiffCallback.areContentsTheSame(
                oldItem.value as DrawerApp,
                newItem.value as DrawerApp
            )
        }
    }

    override fun areItemsTheSame(oldItem: DrawerAppItem, newItem: DrawerAppItem): Boolean {
        if (oldItem.type != newItem.type) return false

        return when (oldItem.type) {
            Type.HEADER -> headerItemDiffCallback.areItemsTheSame(
                oldItem.value as DrawerAppHeader,
                newItem.value as DrawerAppHeader
            )
            Type.ENTRY -> entryItemDiffCallback.areItemsTheSame(
                oldItem.value as DrawerApp,
                newItem.value as DrawerApp
            )
        }
    }

    private class HeaderItemDiffCallback : DiffUtil.ItemCallback<DrawerAppHeader>() {
        override fun areItemsTheSame(oldItem: DrawerAppHeader, newItem: DrawerAppHeader): Boolean = true

        override fun areContentsTheSame(oldItem: DrawerAppHeader, newItem: DrawerAppHeader): Boolean = true
    }

    private class EntryItemDiffCallback : DiffUtil.ItemCallback<DrawerApp>() {
        override fun areItemsTheSame(oldItem: DrawerApp, newItem: DrawerApp): Boolean = oldItem.label == newItem.label

        override fun areContentsTheSame(oldItem: DrawerApp, newItem: DrawerApp): Boolean =
            oldItem.packageName == newItem.packageName && oldItem.activityName == newItem.activityName
    }
}

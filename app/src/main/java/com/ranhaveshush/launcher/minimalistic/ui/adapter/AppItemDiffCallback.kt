package com.ranhaveshush.launcher.minimalistic.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ranhaveshush.launcher.minimalistic.vo.AppItem

class AppItemDiffCallback : DiffUtil.ItemCallback<AppItem>() {

    override fun areItemsTheSame(oldItem: AppItem, newItem: AppItem) = oldItem.packageName == newItem.packageName

    override fun areContentsTheSame(oldItem: AppItem, newItem: AppItem) = oldItem == newItem
}

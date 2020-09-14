package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.databinding.ListHeaderDrawerAppBinding
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemDrawerAppBinding
import com.ranhaveshush.launcher.minimalistic.ui.item.DrawerAppItem
import com.ranhaveshush.launcher.minimalistic.ui.item.DrawerAppItemDiffCallback
import com.ranhaveshush.launcher.minimalistic.ui.listener.DrawerAppItemClickListener
import com.ranhaveshush.launcher.minimalistic.ui.listener.DrawerAppItemLongClickListener
import com.ranhaveshush.launcher.minimalistic.vo.DrawerApp
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppHeader

class DrawerAppsAdapter(
    private val itemClickListener: DrawerAppItemClickListener,
    private val itemLongClickListener: DrawerAppItemLongClickListener
) : ListAdapter<DrawerAppItem, DrawerAppViewHolder>(DrawerAppItemDiffCallback()) {
    override fun getItemViewType(position: Int): Int =
        getItem(position).type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerAppViewHolder =
        when (DrawerAppItem.Type.values()[viewType]) {
            DrawerAppItem.Type.HEADER -> DrawerAppHeaderViewHolder.create(parent)
            DrawerAppItem.Type.ENTRY -> DrawerAppEntryViewHolder.create(
                parent,
                itemClickListener,
                itemLongClickListener
            )
        }

    override fun onBindViewHolder(holder: DrawerAppViewHolder, position: Int) = when (holder) {
        is DrawerAppHeaderViewHolder -> holder.bind(getItem(position).value as DrawerAppHeader)
        is DrawerAppEntryViewHolder -> holder.bind(getItem(position).value as DrawerApp)
    }
}

sealed class DrawerAppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class DrawerAppHeaderViewHolder(private val binding: ListHeaderDrawerAppBinding) : DrawerAppViewHolder(binding.root) {
    fun bind(item: DrawerAppHeader) {
        binding.apply {
            header = item
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): DrawerAppHeaderViewHolder {
            val binding = ListHeaderDrawerAppBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return DrawerAppHeaderViewHolder(binding)
        }
    }
}

class DrawerAppEntryViewHolder(private val binding: ListItemDrawerAppBinding) : DrawerAppViewHolder(binding.root) {
    fun bind(item: DrawerApp) {
        binding.apply {
            root.tag = item
            app = item
            executePendingBindings()
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClickListener: DrawerAppItemClickListener,
            itemLongClickListener: DrawerAppItemLongClickListener
        ): DrawerAppEntryViewHolder {
            val binding = ListItemDrawerAppBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            binding.root.setOnClickListener {
                itemClickListener.onAppClick(it.tag as DrawerApp)
            }
            binding.root.setOnLongClickListener {
                itemLongClickListener.onAppLongClick(it.tag as DrawerApp)
            }

            return DrawerAppEntryViewHolder(binding)
        }
    }
}

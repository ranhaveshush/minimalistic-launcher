package com.ranhaveshush.launcher.minimalistic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.launcher.minimalistic.vo.Repo
import com.ranhaveshush.launcher.minimalistic.databinding.ListItemRepoBinding

class RepoAdapter : ListAdapter<Repo, RepoViewHolder>(RepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ListItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RepoViewHolder(private val binding: ListItemRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Repo) {
        binding.apply {
            repo = item
            executePendingBindings()
        }
    }
}

class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
}

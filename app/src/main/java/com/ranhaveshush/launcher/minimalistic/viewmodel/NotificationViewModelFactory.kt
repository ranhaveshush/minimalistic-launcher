package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository

class NotificationViewModelFactory(
    private val repository: NotificationRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NotificationViewModel(repository) as T
    }
}
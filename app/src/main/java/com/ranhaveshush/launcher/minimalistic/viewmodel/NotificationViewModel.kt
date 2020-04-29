package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.launcher.minimalistic.launcher.NotificationsLauncher
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.vo.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val repository: NotificationRepository,
    private val notificationsLauncher: NotificationsLauncher
) : ViewModel(), NotificationsLauncher {
    val notifications: LiveData<Resource<List<NotificationItem>>> = repository.getAllNotifications().asLiveData(Dispatchers.Default)

    override fun launch(notificationItem: NotificationItem) {
        notificationsLauncher.launch(notificationItem)
        remove(notificationItem)
    }

    fun remove(notificationItem: NotificationItem) = viewModelScope.launch {
        repository.remove(notificationItem.key)
    }
}
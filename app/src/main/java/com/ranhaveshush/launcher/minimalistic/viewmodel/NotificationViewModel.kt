package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.launcher.minimalistic.launcher.NotificationsLauncher
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository
import com.ranhaveshush.launcher.minimalistic.ui.item.NotificationItem
import com.ranhaveshush.launcher.minimalistic.vo.Notification
import com.ranhaveshush.launcher.minimalistic.vo.NotificationHeader
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NotificationViewModel @ViewModelInject constructor(
    private val repository: NotificationRepository,
    private val notificationsLauncher: NotificationsLauncher
) : ViewModel(), NotificationsLauncher {
    val notifications: LiveData<Resource<List<NotificationItem>>> = repository.getAllNotifications().map { resource ->
        if (resource.data == null) {
            Resource.empty()
        } else {
            val notificationItems = mutableListOf(
                NotificationItem(NotificationItem.Type.HEADER, NotificationHeader())
            )
            resource.data.mapTo(notificationItems) { notificationEntry ->
                NotificationItem(NotificationItem.Type.ENTRY, notificationEntry)
            }
            Resource.success<List<NotificationItem>>(notificationItems)
        }
    }.asLiveData(Dispatchers.Default)

    override fun launch(notification: Notification) {
        notificationsLauncher.launch(notification)
        remove(notification)
    }

    fun remove(notificationItem: Notification) = viewModelScope.launch {
        repository.remove(notificationItem.key)
    }

    fun clearAll() = viewModelScope.launch {
        repository.clearAll()
    }
}

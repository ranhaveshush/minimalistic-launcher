package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ranhaveshush.launcher.minimalistic.repository.NotificationRepository

class NotificationViewModel(repository: NotificationRepository) : ViewModel() {
    val notifications = repository.getAllNotifications().asLiveData()
}
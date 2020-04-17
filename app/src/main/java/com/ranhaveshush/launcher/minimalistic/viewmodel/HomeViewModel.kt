package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.vo.AppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {
    val apps: LiveData<Resource<List<AppItem>>> = liveData {
        emitSource(repository.listApps().asLiveData())
    }
}

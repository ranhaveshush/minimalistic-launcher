package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.vo.DrawerAppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource

class AppDrawerViewModel(private val repository: AppDrawerRepository) : ViewModel() {
    val searchQuery = MutableLiveData<String>()

    init {
        searchQuery.value = ""
    }

    val apps: LiveData<Resource<List<DrawerAppItem>>> = searchQuery.switchMap {
        listApps(it)
    }

    private fun listApps(searchQuery: String): LiveData<Resource<List<DrawerAppItem>>> = liveData {
        emitSource(repository.listApps(searchQuery).asLiveData())
    }
}

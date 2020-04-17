package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.vo.AppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    val searchQuery = MutableLiveData<String>()

    init {
        searchQuery.value = ""
    }

    val apps: LiveData<Resource<List<AppItem>>> = searchQuery.switchMap {
        listApps(it)
    }

    private fun listApps(searchQuery: String): LiveData<Resource<List<AppItem>>> = liveData {
        emitSource(repository.listApps(searchQuery).asLiveData())
    }
}

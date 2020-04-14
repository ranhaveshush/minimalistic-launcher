package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository

class HomeViewModelFactory(
    private val repository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HomeViewModel(repository) as T
    }
}

package com.ranhaveshush.launcher.minimalistic.viewmodel

import androidx.lifecycle.*
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.vo.Repo
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    val username = MutableLiveData<String>()

    val repos = username.switchMap { username ->
        listRepos(username)
    }

    private fun listRepos(username: String): LiveData<Resource<List<Repo>>> = liveData {
        emitSource(repository.listRepos(username).asLiveData(Dispatchers.IO))
    }
}

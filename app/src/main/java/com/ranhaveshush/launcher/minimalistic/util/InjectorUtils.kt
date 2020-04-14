package com.ranhaveshush.launcher.minimalistic.util

import com.ranhaveshush.launcher.minimalistic.api.github.GitHubApi
import com.ranhaveshush.launcher.minimalistic.api.github.GitHubClient
import com.ranhaveshush.launcher.minimalistic.repository.HomeRepository
import com.ranhaveshush.launcher.minimalistic.viewmodel.HomeViewModelFactory

object InjectorUtils {

    fun provideHomeViewModelFactory() = HomeViewModelFactory(getHomeRepository())

    fun getHomeRepository() = HomeRepository(getGitHubClient())

    private fun getGitHubClient() = GitHubClient(GitHubApi)
}

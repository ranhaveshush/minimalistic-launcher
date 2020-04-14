package com.ranhaveshush.launcher.minimalistic.api.github

import com.ranhaveshush.launcher.minimalistic.api.ApiResponse
import com.ranhaveshush.launcher.minimalistic.vo.Repo

class GitHubClient(private val api: GitHubApi) {

    suspend fun listRepos(username: String): ApiResponse<List<Repo>> {
        val response = api.service.listRepos(username)
        return ApiResponse.create(response)
    }
}

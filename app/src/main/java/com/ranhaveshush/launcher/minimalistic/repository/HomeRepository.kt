package com.ranhaveshush.launcher.minimalistic.repository

import com.ranhaveshush.launcher.minimalistic.api.EmptyApiResponse
import com.ranhaveshush.launcher.minimalistic.api.ErrorApiResponse
import com.ranhaveshush.launcher.minimalistic.api.SuccessApiResponse
import com.ranhaveshush.launcher.minimalistic.api.github.GitHubClient
import com.ranhaveshush.launcher.minimalistic.vo.Repo
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository(private val client: GitHubClient) {

    suspend fun listRepos(username: String): Flow<Resource<List<Repo>>> = flow {
        emit(Resource.loading())

        val resource = try {
            when (val apiResponse = client.listRepos(username)) {
                is SuccessApiResponse -> Resource.success(apiResponse.data)
                is EmptyApiResponse -> Resource.empty()
                is ErrorApiResponse -> Resource.error(apiResponse.message)
            }
        } catch (e: Exception) {
            Resource.error<List<Repo>>(e.message!!, e.cause)
        }

        emit(resource)
    }
}

package com.ranhaveshush.launcher

import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeRepositoryTest {

    private val repository = InjectorUtils.getHomeRepository()

    @Test
    fun listRepos_isNotEmpty() {
        val username = "ranhaveshush"

        val resource = runBlocking {
            repository.listRepos(username).collect {
                when (it.state.status) {
                    Resource.Status.SUCCESS -> assert(it.data.isNullOrEmpty())
                    Resource.Status.FAILURE -> Assert.fail(it.state.message)
                }
            }
        }
    }
}

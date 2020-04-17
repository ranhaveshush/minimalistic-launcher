package com.ranhaveshush.launcher

import androidx.test.platform.app.InstrumentationRegistry
import com.ranhaveshush.launcher.minimalistic.repository.AppDrawerRepository
import com.ranhaveshush.launcher.minimalistic.util.InjectorUtils
import com.ranhaveshush.launcher.minimalistic.vo.AppItem
import com.ranhaveshush.launcher.minimalistic.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AppDrawerRepositoryTest {

    private lateinit var repository: AppDrawerRepository

    @Before
    fun setup() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val packageManager = instrumentation.context.packageManager

        repository = InjectorUtils().getAppDrawerRepository(packageManager)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun listApps_isNotNullOrEmpty() = runBlocking {
        var apps: List<AppItem>? = null

        repository.listApps().collect { resource ->
            if (resource.state == Resource.State.SUCCESS) {
                apps = resource.data
            }
        }

        assert(!apps.isNullOrEmpty())
    }
}

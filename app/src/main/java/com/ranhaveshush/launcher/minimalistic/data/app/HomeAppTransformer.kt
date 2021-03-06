package com.ranhaveshush.launcher.minimalistic.data.app

import android.content.pm.ResolveInfo
import com.ranhaveshush.launcher.minimalistic.data.DataTransformer
import com.ranhaveshush.launcher.minimalistic.vo.HomeApp

interface HomeAppTransformer : DataTransformer<ResolveInfo, HomeApp>

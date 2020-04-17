package com.ranhaveshush.launcher.minimalistic.ui.fragment

import android.app.Application
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

val Fragment.application: Application
    get() = requireActivity().application

val Fragment.packageManager: PackageManager
    get() = requireContext().packageManager
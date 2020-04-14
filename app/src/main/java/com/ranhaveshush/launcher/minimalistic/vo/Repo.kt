package com.ranhaveshush.launcher.minimalistic.vo

import com.squareup.moshi.Json

data class Repo(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "full_name") val fullName: String,
    @field:Json(name = "description") val description: String
)

package com.example.composemvvm.data.models.api_models

import com.google.gson.annotations.SerializedName

data class LoginRequest(val userName: String, val password: String)

data class LoginData(
    @SerializedName("userId") var userId: String? = null,
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("accessToken") var accessToken: String? = null,
    @SerializedName("refreshToken") var refreshToken: String? = null
)

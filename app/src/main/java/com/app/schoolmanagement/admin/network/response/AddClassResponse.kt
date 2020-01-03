package com.app.schoolmanagement.admin.network.response


import com.google.gson.annotations.SerializedName

data class AddClassResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?
)
package com.app.schoolmanagement.admin.network.response


import com.google.gson.annotations.SerializedName

data class StaffAddResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?
)
package com.app.schoolmanagement.admin.network.response


import com.app.schoolmanagement.students.room.entity.School
import com.google.gson.annotations.SerializedName

data class AdminlLoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("response")
    val response: Response?=null,
    @SerializedName("success")
    val success: Boolean?
) {
    data class Response(
        @SerializedName("id")
        val id: String?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("username")
        val username: String?
    )
}
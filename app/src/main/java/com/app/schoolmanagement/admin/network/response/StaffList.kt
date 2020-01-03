package com.app.schoolmanagement.admin.network.response


import com.google.gson.annotations.SerializedName

data class StaffList(
    @SerializedName("staff_list")
    var staffList: List<Staff?>?=null
) {
    data class Staff(
        @SerializedName("class_name")
        val className: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("is_incharge")
        val isIncharge: String?,
        @SerializedName("is_staff")
        val isStaff: String?,
        @SerializedName("is_teacher")
        val isTeacher: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("password")
        val password: String?
    )
}
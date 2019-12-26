package com.app.schoolmanagement.students.room.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
const val STUDENT_ID = 0

@Entity
data class Student(
    @SerializedName("message")
    val message: String?,
    @SerializedName("response")
    val response: Response? = null,
    @SerializedName("success")
    val success: Boolean?
) {
    @PrimaryKey(autoGenerate= false)
    var studentId= STUDENT_ID
    data class Response(
        @SerializedName("address")
        val address: String?,
        @SerializedName("gender")
        val gender: String?,
        @SerializedName("guardian_name")
        val guardianName: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("roll_no")
        val rollNo: String?,
        @SerializedName("school_id")
        val schoolId: String?,
        @SerializedName("school_name")
        val schoolName: String?,
        @SerializedName("student_id")
        val studentId: String?
    )
}
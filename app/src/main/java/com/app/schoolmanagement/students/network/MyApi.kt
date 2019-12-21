package com.app.schoolmanagement.students.network

import com.app.schoolmanagement.students.network.response.SchoolLoginResponse
import com.app.schoolmanagement.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("school_login.php")
    suspend fun school_login(
        @Field("school_name") school_name: String,
        @Field("password") password: String
    ): Response<SchoolLoginResponse>

    @FormUrlEncoded
    @POST("student_signup.php")
    suspend fun student_signup(
        @Field("school_id") school_id:String,
        @Field("class_name") class_name:String,
        @Field("section_name") section_name:String,
        @Field("roll_no") roll_no:String,
        @Field("password") password: String
    )

    companion object{
        operator  fun invoke():MyApi
        {
            return Retrofit
                .Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
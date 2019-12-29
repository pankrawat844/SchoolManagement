package com.app.schoolmanagement.students.network

import com.app.schoolmanagement.students.network.response.Classes
import com.app.schoolmanagement.students.network.response.SchoolLoginResponse
import com.app.schoolmanagement.students.network.response.Student
import com.app.schoolmanagement.utils.Constants
import com.app.schoolmanagement.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
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
        @Field("school_id") school_id: String,
        @Field("class_name") class_name: String,
        @Field("section_name") section_name: String,
        @Field("roll_no") roll_no: String,
        @Field("password") password: String
    ): Response<Student>

    @FormUrlEncoded
    @POST("student_login.php")
    suspend fun student_login(
        @Field("school_id") school_id: String,
        @Field("roll_no") roll_no: String,
        @Field("password") password: String
    ): Response<Student>

    @FormUrlEncoded
    @POST("class_list.php")
    fun get_classes(@Field("school_id") school_id: String): Call<Classes>

    @FormUrlEncoded
    @POST("section_list.php")
    fun get_Section(@Field("class_name") class_name: String): Call<Classes>

    @FormUrlEncoded
    @POST("edit_profile.php")
    fun edit_profile(
        @Field("school_id") school_id: String,
        @Field("class_id") class_id: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Response<String>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit
                .Builder()
                .baseUrl(Constants.base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
package com.app.schoolmanagement.students.network

import com.app.schoolmanagement.students.network.response.*
import com.app.schoolmanagement.students.utils.Constants
import com.app.schoolmanagement.students.utils.NetworkConnectionInterceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

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
        @Field("student_id") student_id: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Call<ResponseBody>


    @Multipart
    @POST("teacher_api/time_table.php")
    fun upload_timetable(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("teacher_api/timetable_list.php")
    fun get_timetable(
        @Field("class_name") class_name: String,
        @Field("section_name") notice: String
    ): Call<Timetable>


    @Multipart
    @POST("businfo.php")
    fun upload_businfo(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("businfo_list.php")
    fun get_busInfo(
        @Field("class_id") class_name: String
    ): Call<Timetable>


    @Multipart
    @POST("leave.php")
    fun upload_leave(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("leave_detail.php")
    fun get_leave(
        @Field("class_name") class_name: String,
        @Field("section_name") notice: String
    ): Call<Timetable>

    @Multipart
    @POST("fee_info.php")
    fun upload_feeInfo(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("fee_info_detail.php")
    fun get_feeInfo(
        @Field("class_id") class_id: String
    ): Call<Timetable>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit
                .Builder()
                .baseUrl(Constants.base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
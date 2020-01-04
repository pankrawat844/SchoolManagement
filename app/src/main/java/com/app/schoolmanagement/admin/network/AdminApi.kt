package com.app.schoolmanagement.admin.network

import com.app.schoolmanagement.admin.network.response.AddClassResponse
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.utils.Constants
import com.app.schoolmanagement.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AdminApi {

    companion object
    {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): AdminApi
        {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.base_url)
                .build()
                .create(AdminApi::class.java)
        }

    }
    @FormUrlEncoded
    @POST("admin_login.php")
    fun admin_login(@Field("user_name") user_name:String,
                    @Field("password") password: String
    ): Call<AdminlLoginResponse>


    @FormUrlEncoded
    @POST("add_class.php")
    fun add_class(@Field("school_id") school_id:String,
                        @Field("class_name") class_name:String,
                    @Field("section_name") section_name:String,
                    @Field("total_student") tottal_student:String):Call<AddClassResponse>

    @GET("staff_list.php")
    fun getStaffList():Call<StaffList>

    @FormUrlEncoded
    @POST("class_list.php")
    fun get_classes(@Field("school_id") school_id: String): Call<Classes>

    @FormUrlEncoded
    @POST("section_list.php")
    fun get_Section(@Field("class_name") class_name: String): Call<Classes>

}
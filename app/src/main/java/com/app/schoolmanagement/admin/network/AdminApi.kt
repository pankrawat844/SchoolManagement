package com.app.schoolmanagement.admin.network

import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.utils.Constants
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AdminApi {

    companion object
    {
        operator fun invoke():AdminApi
        {
            return Retrofit.Builder()
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
                    @Field("password") password:String):Response<AdminlLoginResponse>
}
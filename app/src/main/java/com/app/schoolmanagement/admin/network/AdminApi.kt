package com.app.schoolmanagement.admin.network

import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.utils.Constants
import com.app.schoolmanagement.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
}
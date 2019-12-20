package com.app.schoolmanagement.students.network

import com.app.schoolmanagement.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("school_login.php")
    fun school_login(@Field("school_name") school_name:String,
                     @Field("password") password:String):Call<ResponseBody>

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
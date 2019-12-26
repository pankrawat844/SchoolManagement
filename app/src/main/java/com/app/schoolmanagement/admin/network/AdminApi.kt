package com.app.schoolmanagement.admin.network

import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.utils.Constants
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AdminApi {

    companion object
    {
        operator fun invoke():AdminApi
        {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.base_url)
                .build()
                .create(AdminApi::class.java)
        }

    }


}
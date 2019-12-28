package com.app.schoolmanagement.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val applicationcontext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvialable())
            throw NoInternetException("Make Sure You Have Active Network Internet.")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvialable(): Boolean {
        val connectivityManager =
            applicationcontext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isAvailable
        }
    }
}
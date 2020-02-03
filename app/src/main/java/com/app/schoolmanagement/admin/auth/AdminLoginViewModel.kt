package com.app.schoolmanagement.admin.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.utils.ApiException
import com.app.schoolmanagement.students.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminLoginViewModel(val adminLoginRepository: AdminRepository) : ViewModel()
{
    var user_name:String?=null
    var password:String?=null
    var adminLoginListener: AdminLoginListener?=null

    fun onLoginClick(view:View)
    {
        adminLoginListener?.onStarted()
        if(user_name.isNullOrEmpty() || password.isNullOrEmpty())
        {
            adminLoginListener?.onFailure("Username Or Password Must Not Be Empty.")
            return
        }


        CoroutineScope(Dispatchers.Main).launch {

            adminLoginRepository.adminLogin(user_name!!, password!!)
                .enqueue(object : Callback<AdminlLoginResponse> {
                    override fun onFailure(call: Call<AdminlLoginResponse>, t: Throwable) {
                        adminLoginListener?.onFailure(t.message!!)
                    }

                    override fun onResponse(
                        call: Call<AdminlLoginResponse>,
                        response: Response<AdminlLoginResponse>
                    ) {
                        try {
                            if (response.isSuccessful) {
                                response.body()?.response.let {
                                    adminLoginListener?.onSuccess(it!!)
                                    return
                                }
                                adminLoginListener?.onFailure(
                                    JSONObject(response.errorBody()?.string()).getString(
                                        "message"
                                    )
                                )

                            } else
                                adminLoginListener?.onFailure(
                                    JSONObject(response.errorBody()?.string()).getString(
                                        "message"
                                    )
                                )
                        } catch (e: ApiException) {
                            adminLoginListener?.onFailure(e.message!!)
                        } catch (e: NoInternetException) {
                            adminLoginListener?.onFailure(e.message!!)
                        }
                    }


                })

        }

    }
}
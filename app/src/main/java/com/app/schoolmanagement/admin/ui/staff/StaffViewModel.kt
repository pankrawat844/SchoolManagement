package com.app.schoolmanagement.admin.ui.staff

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.admin.network.response.StaffAddResponse
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.utils.ApiException
import com.app.schoolmanagement.students.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StaffViewModel(val adminRepository: AdminRepository) : ViewModel() {
    var ctx: Activity? = null
    var staffActivityListener: StaffActivityListener? = null
    var password: String? = null
    var dialog:AlertDialog?=null


    var selected_class:String?=null
    var selected_section:String?=null
    var staff_id:String?=null
    var isinhcarge: Boolean? = false

    var staffList = MutableLiveData<List<StaffList.Staff>>()
    fun getStaff(): MutableLiveData<List<StaffList.Staff>> {
        staffActivityListener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            async {
                adminRepository.getStaff().enqueue(object : Callback<StaffList> {
                    override fun onFailure(call: Call<StaffList>, t: Throwable) {
                        staffActivityListener?.onError(t.message!!)
                    }

                    override fun onResponse(call: Call<StaffList>, response: Response<StaffList>) {
                        val data = response.body()
                        staffList.value = (data?.staffList as List<StaffList.Staff>?)!!
                        staffActivityListener?.onSuccess(   "")

                    }

                })
            }.await()
        }
        return staffList
    }

    fun onAddStaff(view: View) {

            if (selected_class.isNullOrEmpty() || selected_section.isNullOrEmpty() || staff_id.isNullOrEmpty()|| password.isNullOrEmpty())
                Toast.makeText(ctx, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            else {
                CoroutineScope(Dispatchers.Main).launch {
                    staffActivityListener?.onStarted()
                   adminRepository.addstaff(
                        "1",
                        selected_class!!,
                        selected_section!!,
                        staff_id!!,
                        password!!,
                       isinhcarge!!
                   ).enqueue(object : Callback<StaffAddResponse> {
                       override fun onFailure(call: Call<StaffAddResponse>, t: Throwable) {
                            staffActivityListener?.onError(t.message!!)
                            dialog?.dismiss()

                        }

                        override fun onResponse(
                            call: Call<StaffAddResponse>,
                            response: Response<StaffAddResponse>
                        ) {
                            try {
                                if (response.isSuccessful) {
                                val res = response.body()
                                if (res?.success!!) {
                                    staffActivityListener?.onSuccess(res.message!!)
//                                    staffActivityListener?.onDataChanged(res.message!!)
                                    dialog?.dismiss()
                                } else {
                                    staffActivityListener?.onError(res.message!!)
                                    dialog?.dismiss()

                                }
                                } else {
                                    val jsonobj = JSONObject(response.errorBody()?.string())
                                    Log.e("TAG", "onResponse: " + jsonobj.toString())
                                }
                            } catch (e: ApiException) {
                                staffActivityListener?.onError(e.message!!)
                                dialog?.dismiss()

                            } catch (e: NoInternetException) {
                                staffActivityListener?.onError(e.message!!)
                                dialog?.dismiss()

                            }

                        }

                    })


                }
            }
//
    }

    fun getClasses() {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                adminRepository.getClasses("1").enqueue(object : Callback<Classes> {
                    override fun onFailure(call: Call<Classes>, t: Throwable) {
                        staffActivityListener?.onError(t.message!!)
                    }

                    override fun onResponse(call: Call<Classes>, response: Response<Classes>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                staffActivityListener?.onClassSuccess(it)
                            }
                        }
                    }
                })
            } catch (e: ApiException) {
                staffActivityListener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                staffActivityListener?.onError(e.message!!)
            }
        }

    }



    suspend fun getSection(class_name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                adminRepository.getSection(class_name).enqueue(object : Callback<Classes> {
                    override fun onFailure(call: Call<Classes>, t: Throwable) {
                        staffActivityListener?.onError(t.message!!)
                    }

                    override fun onResponse(call: Call<Classes>, response: Response<Classes>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                staffActivityListener?.onSectionSuccess(it)

                            }
                        }
                    }
                })
            } catch (e: ApiException) {
                staffActivityListener?.onError(e.message!!)

            } catch (e: NoInternetException) {
                staffActivityListener?.onError(e.message!!)
            }
        }
    }
}
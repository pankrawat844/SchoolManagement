package com.app.schoolmanagement.admin.ui.staff

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.admin.repositories.AdminRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StaffViewModel(val adminRepository: AdminRepository) : ViewModel() {
    var ctx: Activity? = null
    var staffActivityListener: StaffActivityListener? = null
    var class_name: String? = null
    var section_name: String? = null
    var id: String? = null
    var password: String? = null

    var staffList = MutableLiveData<List<StaffList.Staff>>()
    fun getStaff(): MutableLiveData<List<StaffList.Staff>> {
        CoroutineScope(Dispatchers.Main).launch {
            async {
                adminRepository.getStaff().enqueue(object : Callback<StaffList> {
                    override fun onFailure(call: Call<StaffList>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<StaffList>, response: Response<StaffList>) {
                        val data = response.body()
                        staffList.value = (data?.staffList as List<StaffList.Staff>?)!!
                    }

                })
            }.await()
        }
        return staffList
    }

    fun onAddStaff(view: View) {

        val viewGroup: ViewGroup = ctx?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_add_staff, viewGroup, false)

//        dialogView.buttonOk.setOnClickListener {
//            if (dialogView.class_name.text.toString().isNullOrEmpty() || dialogView.section_name.text.toString().isNullOrEmpty() || dialogView.total_student.text.toString().isNullOrEmpty())
//                Toast.makeText(ctx, "All fields are mandatory", Toast.LENGTH_SHORT).show()
//            else {
//                CoroutineScope(Dispatchers.Main).launch {
//                    staffActivityListener?.onStarted()
//                    val response = adminRepository.addclass(
//                        "1",
//                        dialogView.class_name.text.toString(),
//                        dialogView.section_name.text.toString(),
//                        dialogView.total_student.text.toString()
//                    ).enqueue(object : Callback<AddClassResponse> {
//                        override fun onFailure(call: Call<AddClassResponse>, t: Throwable) {
//                            staffActivityListener?.onError(t.message!!)
//                            dialog?.dismiss()
//
//                        }
//
//                        override fun onResponse(
//                            call: Call<AddClassResponse>,
//                            response: Response<AddClassResponse>
//                        ) {
//                            try {
//
//                                val res = response.body()
//                                if (res?.success!!) {
//                                    staffActivityListener?.onDataChanged(res.message!!)
//                                    dialog?.dismiss()
//                                } else {
//                                    staffActivityListener?.onError(res.message!!)
//                                    dialog?.dismiss()
//
//                                }
//                            } catch (e: ApiException) {
//                                staffActivityListener?.onError(e.message!!)
//                                dialog?.dismiss()
//
//                            } catch (e: NoInternetException) {
//                                staffActivityListener?.onError(e.message!!)
//                                dialog?.dismiss()
//
//                            }
//
//                        }
//
//                    })
//
//
//                }
//            }
//        }
//        val builder: AlertDialog.Builder = AlertDialog.Builder(view.context)
//        builder.setView(dialogView)
//        dialog = builder.create()
//        dialog?.show()
    }
}
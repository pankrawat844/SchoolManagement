package com.app.schoolmanagement.admin.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.network.response.AddClassResponse
import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.admin.ui.staff.StaffActivity
import com.app.schoolmanagement.students.utils.ApiException
import com.app.schoolmanagement.students.utils.NoInternetException
import kotlinx.android.synthetic.main.dialog_add_class.view.*
import kotlinx.android.synthetic.main.dialog_edit_profile.view.buttonOk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdminHomeViewModel(val adminRepository: AdminRepository) : ViewModel() {
    var class_name: String? = null
    var section_name: String? = null
    var total_student: String? = null

    var view1: Activity? = null
    var school_id: String? = null
    var homeFragmentListener: AdminHomeFragmentListener? = null
    var dialog: AlertDialog? = null
    var class_list:Classes?=null
    var section_list:Classes?=null
    fun onddclass(view: View) {

        val viewGroup: ViewGroup = view1?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_add_class, viewGroup, false)

        dialogView.buttonOk.setOnClickListener {
            if (dialogView.class_name.text.toString().isNullOrEmpty() || dialogView.section_name.text.toString().isNullOrEmpty() || dialogView.total_student.text.toString().isNullOrEmpty())
                Toast.makeText(view1, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            else {
                CoroutineScope(Dispatchers.Main).launch {
                    homeFragmentListener?.onStarted()
                    val response = adminRepository.addclass(
                        "1",
                        dialogView.class_name.text.toString(),
                        dialogView.section_name.text.toString(),
                        dialogView.total_student.text.toString()
                    ).enqueue(object : Callback<AddClassResponse> {
                        override fun onFailure(call: Call<AddClassResponse>, t: Throwable) {
                            homeFragmentListener?.onError(t.message!!)
                            dialog?.dismiss()

                        }

                        override fun onResponse(
                            call: Call<AddClassResponse>,
                            response: Response<AddClassResponse>
                        ) {
                            try {

                                val res = response.body()
                                if (res?.success!!) {
                                    homeFragmentListener?.onDataChanged(res.message!!)
                                    dialog?.dismiss()
                                } else {
                                    homeFragmentListener?.onError(res.message!!)
                                    dialog?.dismiss()

                                }
                            } catch (e: ApiException) {
                                homeFragmentListener?.onError(e.message!!)
                                dialog?.dismiss()

                            } catch (e: NoInternetException) {
                                homeFragmentListener?.onError(e.message!!)
                                dialog?.dismiss()

                            }

                        }

                    })


                }
            }
        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(view.context)
        builder.setView(dialogView)
        dialog = builder.create()
        dialog?.show()
    }

    fun addStaff(view: View)
    {
        Intent(view.context,StaffActivity::class.java).also {
            view.context.startActivity(it)
        }
    }





}
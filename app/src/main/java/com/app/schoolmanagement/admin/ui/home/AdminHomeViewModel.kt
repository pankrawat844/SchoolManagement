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
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.admin.ui.staff.StaffActivity
import com.app.schoolmanagement.students.home.ui.home.AdminHomeFragmentListener
import com.app.schoolmanagement.utils.ApiException
import com.app.schoolmanagement.utils.NoInternetException
import kotlinx.android.synthetic.main.dialog_edit_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class AdminHomeViewModel(private val adminRepository: AdminRepository) : ViewModel() {
    var name: String? = null
    var mobile: String? = null
    var password: String? = null
    var view1: Activity? = null
    var student_id: String? = null
    var homeFragmentListener: AdminHomeFragmentListener? = null
    var dialog: AlertDialog? = null
    fun onddclass(view: View) {

        val viewGroup: ViewGroup = view1?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_edit_profile, viewGroup, false)
        dialogView.name.setText(name)
        dialogView.mobile_no.setText(mobile)
        dialogView.password.setText(password)
        dialogView.buttonOk.setOnClickListener {
            if (dialogView.name.toString().isEmpty() || dialogView.mobile_no.toString().isEmpty() || dialogView.password.toString().isEmpty())
                Toast.makeText(view1, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {


                      val response=  adminRepository.addclass(
                            student_id!!,"","",""
                        )
                    val response_json=JSONObject(response)
                        if(response_json.getBoolean("success")){
                            homeFragmentListener?.onDataChanged(response_json.getString("message"))
                        }else
                        {
                            homeFragmentListener?.onError(response_json.getString("message"))

                        }
                    } catch (e: ApiException) {

                    }catch (e:NoInternetException){

                    }

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
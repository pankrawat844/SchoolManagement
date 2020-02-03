package com.app.schoolmanagement.students.home.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.R
import com.app.schoolmanagement.students.businfo.BusInfoActivity
import com.app.schoolmanagement.students.feeinfo.FeeInfoActivity
import com.app.schoolmanagement.students.leave.LeaveActivity
import com.app.schoolmanagement.students.network.response.Timetable
import com.app.schoolmanagement.students.repositories.StudentRepository
import com.app.schoolmanagement.students.timetable.TimeTableActivity
import com.app.schoolmanagement.students.utils.ApiException
import com.app.schoolmanagement.students.utils.NoInternetException
import kotlinx.android.synthetic.main.dialog_edit_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call

class HomeViewModel(val studentRepository: StudentRepository) : ViewModel() {
    var name: String? = null
    var mobile: String? = null
    var password: String? = null
    var view1: Activity? = null
    var student_id: String? = null
    var homeFragmentListener: HomeFragmentListener? = null
    var dialog: AlertDialog? = null
    fun onedit_profile(view: View) {

        val viewGroup: ViewGroup = view1?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_edit_profile, viewGroup, false)
        dialogView.name.setText(name)
        dialogView.mobile_no.setText(mobile)
        dialogView.password.setText(password)
        dialogView.buttonOk.setOnClickListener {
            if (dialogView.name.toString().length == 0 || dialogView.mobile_no.toString().length == 0 || dialogView.password.toString().length == 0)
                Toast.makeText(view1, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            else {
                CoroutineScope(Dispatchers.Main).launch {
                    try {

                        studentRepository.edit_profile(
                            dialogView.name.text.toString(),
                            dialogView.mobile_no.text.toString(),
                            dialogView.password.text.toString(),
                            student_id!!
                        )
                            .enqueue(object : retrofit2.Callback<ResponseBody> {
                                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                                }

                                override fun onResponse(
                                    call: Call<ResponseBody>,
                                    response: retrofit2.Response<ResponseBody>
                                ) {
                                    response.body().let {
                                        val data = it?.string()
                                        val jsonObject = JSONObject(data)
                                        if (jsonObject.getBoolean("success")) {
                                            Toast.makeText(
                                                view1,
                                                jsonObject.getString("message"),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            homeFragmentListener?.onDataChanged(dialogView.name.text.toString())
                                            val sharedPreferences =
                                                view1?.getSharedPreferences("app", 0)
                                            sharedPreferences?.edit().also {
                                                it?.putString(
                                                    "name",
                                                    dialogView.name.text.toString()
                                                )
                                                it?.putString(
                                                    "mobile",
                                                    dialogView.mobile_no.text.toString()
                                                )
                                                it?.putString(
                                                    "password",
                                                    dialogView.password.text.toString()
                                                )
                                                it?.commit()
                                            }
                                            dialog?.dismiss()
                                        } else
                                            Toast.makeText(
                                                view1,
                                                jsonObject.getString("message"),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        dialog?.dismiss()
                                        return
                                    }
                                    Toast.makeText(
                                        view1,
                                        response.errorBody()?.string(),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }

                            })
                    } catch (e: ApiException) {
                        homeFragmentListener?.onError(e.message!!)
                    } catch (e: NoInternetException) {
                        homeFragmentListener?.onError(e.message!!)
                    }
                }
            }
        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(view.context)
        builder.setView(dialogView)
        dialog = builder.create()
        dialog?.show()
    }



    fun ontimetableclick(view: View){
        Intent(view.context,TimeTableActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onbusinfoclick(view: View){
        Intent(view.context,BusInfoActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onleaveclick(view: View){
        Intent(view.context,LeaveActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onfeeinfoclick(view: View){
        Intent(view.context,FeeInfoActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}
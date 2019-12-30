package com.app.schoolmanagement.students.home.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.R
import com.app.schoolmanagement.students.repositories.StudentRepository
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
    fun onedit_profile(view: View) {

        val viewGroup: ViewGroup = view1?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_edit_profile, viewGroup, false)
        dialogView.name.setText(name)
        dialogView.mobile_no.setText(mobile)
        dialogView.password.setText(password)
        dialogView.buttonOk.setOnClickListener {
            if (dialogView.name.toString().isNullOrEmpty() || dialogView.mobile_no.toString().isNullOrEmpty() || dialogView.password.toString().isNullOrEmpty())
                Toast.makeText(view1, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    studentRepository.edit_profile(name!!, mobile!!, password!!, student_id!!)
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
                                    } else
                                        Toast.makeText(
                                            view1,
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    return
                                }
                                Toast.makeText(
                                    view1,
                                    response.errorBody()?.string(),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                        })
                }
            }
        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(view.context)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()
    }
}
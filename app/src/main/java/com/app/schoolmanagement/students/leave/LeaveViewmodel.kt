package com.app.schoolmanagement.students.leave

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.leave.LeaveListener
import com.app.schoolmanagement.students.network.response.Homework
import com.app.schoolmanagement.students.network.response.Timetable
import com.app.schoolmanagement.students.repositories.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaveViewmodel(val repository: StudentRepository) : ViewModel() {
    var listener: LeaveListener? = null

    suspend fun upload(
        class_name: RequestBody,
        section_name: RequestBody,
        img: MultipartBody.Part
    ) {
        listener?.onStarted()
        repository.uploadLeave(class_name, section_name, img).enqueue(object :
            Callback<Homework> {
            override fun onFailure(call: Call<Homework>, t: Throwable) {
                Log.e("leaveviewmodel", "onFailure: " + t.message)
                listener?.onFailure(t.message!!)

            }

            override fun onResponse(call: Call<Homework>, response: Response<Homework>) {
                if (response.isSuccessful) {
                    Log.e("leaveviewmodel", "onsuccess: " + response.body()!!.response)
                    listener?.onImageSuccess(response.body()!!)
                } else {
                    listener?.onFailure(JSONObject(response.errorBody()?.string()).getString("response"))
                }

            }

        })

    }

    fun leave(
        class_name: String,
        section_name: String
    ) {
        listener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.getLeave(class_name, section_name).enqueue(object : Callback<Timetable> {
                override fun onFailure(call: Call<Timetable>, t: Throwable) {
                    listener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<Timetable>,
                    response: Response<Timetable>
                ) {
                    if (response.isSuccessful)
                        listener?.onSuccess(response.body()!!)
                    else
                        listener?.onFailure(JSONObject(response.errorBody()?.string()).getString("message"))
//                            Log.e("error",response.errorBody()?.string())
                }

            })
        }
    }

}
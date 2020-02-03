package com.app.schoolmanagement.students.feeinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.feeinfo.FeeInfoListener
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

class FeeInfoViewmodel(val repository: StudentRepository) : ViewModel() {
    var Listener: FeeInfoListener? = null

    suspend fun upload(
        class_name: RequestBody,
        section_name: RequestBody,
        img: MultipartBody.Part
    ) {
        Listener?.onStarted()
        repository.uploadFeeInfo(class_name, section_name, img).enqueue(object :
            Callback<Homework> {
            override fun onFailure(call: Call<Homework>, t: Throwable) {
                Log.e("homeviewmodel", "onFailure: " + t.message)
                Listener?.onFailure(t.message!!)

            }

            override fun onResponse(call: Call<Homework>, response: Response<Homework>) {
                if (response.isSuccessful) {
                    Log.e("homeviewmodel", "onsuccess: " + response.body()!!.response)
                    Listener?.onImageSuccess(response.body()!!)
                } else {
                    Listener?.onFailure(JSONObject(response.errorBody()?.string()).getString("response"))
                }

            }

        })

    }

    fun feeInfo(
        class_id: String
    ) {
        Listener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.getFeeInfo(class_id).enqueue(object : Callback<Timetable> {
                override fun onFailure(call: Call<Timetable>, t: Throwable) {
                    Listener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<Timetable>,
                    response: Response<Timetable>
                ) {
                    if (response.isSuccessful)
                        Listener?.onSuccess(response.body()!!)
                    else
                        Listener?.onFailure(JSONObject(response.errorBody()?.string()).getString("message"))
//                            Log.e("error",response.errorBody()?.string())
                }

            })
        }
    }

}
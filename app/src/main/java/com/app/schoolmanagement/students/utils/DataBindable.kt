package com.app.schoolmanagement.students.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.app.schoolmanagement.students.auth.studentsignup.StudentSignupViewModel

class DataBindable {

    companion object {
        @BindingAdapter("selectedItem", requireAll = false)
        @JvmStatic
        fun selected(spinner: Spinner, data: StudentSignupViewModel) {
            spinner.onItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }

            })
        }



    }
}

private operator fun AdapterView.OnItemSelectedListener?.invoke(onItemSelectedListener: AdapterView.OnItemSelectedListener) {

}



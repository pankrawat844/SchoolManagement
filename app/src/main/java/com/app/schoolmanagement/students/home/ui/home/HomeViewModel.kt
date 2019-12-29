package com.app.schoolmanagement.students.home.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.R
import kotlinx.android.synthetic.main.dialog_edit_profile.view.*

class HomeViewModel : ViewModel() {
    var name: String? = null
    var mobile: String? = null
    var password: String? = null
    var view1: Activity? = null
    fun onedit_profile(view: View) {

        val viewGroup: ViewGroup = view1?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_edit_profile, viewGroup, false)
        dialogView.name.setText(name)
        dialogView.mobile_no.setText(mobile)
        dialogView.password.setText(password)
        dialogView.buttonOk.setOnClickListener {

        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(view.context)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()
    }
}
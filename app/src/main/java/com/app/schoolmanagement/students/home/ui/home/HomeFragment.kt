package com.app.schoolmanagement.students.home.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var sharedPreferences:SharedPreferences?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.view1 = activity
        root.viewmodel = homeViewModel

        sharedPreferences=context?.getSharedPreferences("app",MODE_PRIVATE)
        homeViewModel.name = sharedPreferences?.getString("name", "")
        homeViewModel.mobile = sharedPreferences?.getString("mobile", "")
        homeViewModel.password = sharedPreferences?.getString("password", "")
        homeViewModel.student_id = sharedPreferences?.getString("student_id", "")

        if(sharedPreferences?.contains("student_name")!!)
            root.userName.text = "Welcome " + sharedPreferences?.getString("name", "")
        else
            root.userName.text = "Welcome " + sharedPreferences?.getString("roll_no", "")


        return root.root
    }

}

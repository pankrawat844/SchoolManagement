package com.app.schoolmanagement.students.home.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import kotlinx.android.synthetic.main.nav_header_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var sharedPreferences:SharedPreferences?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        sharedPreferences=context?.getSharedPreferences("app",MODE_PRIVATE)
        if(sharedPreferences?.contains("student_name")!!)
            textView.text="Welcome "+sharedPreferences?.getString("student_name","")
        else
            textView.text=sharedPreferences?.getString("roll_no","")
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //            textView.text = it
        })

        return root
    }

}

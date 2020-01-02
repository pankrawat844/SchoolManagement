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
import com.app.schoolmanagement.utils.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class HomeFragment : Fragment(), HomeFragmentListener, KodeinAware {
    override val kodein by kodein()

    private lateinit var homeViewModel: HomeViewModel
    private var sharedPreferences:SharedPreferences?=null
    lateinit var root: FragmentHomeBinding
    private val factoryAdmin: HomeViewModelFactory by instance()
    var rotation: Float = 0.00f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel =
            ViewModelProviders.of(this, factoryAdmin).get(HomeViewModel::class.java)
        homeViewModel.view1 = activity
        root.viewmodel = homeViewModel

        sharedPreferences=context?.getSharedPreferences("app",MODE_PRIVATE)
        homeViewModel.name = sharedPreferences?.getString("name", "")
        homeViewModel.mobile = sharedPreferences?.getString("mobile", "")
        homeViewModel.password = sharedPreferences?.getString("password", "")
        homeViewModel.student_id = sharedPreferences?.getString("admin_id", "")
        homeViewModel.homeFragmentListener = this
        if (sharedPreferences?.contains("name")!!)
            root.userName.text = "Welcome " + sharedPreferences?.getString("name", "")
        else
            root.userName.text = "Welcome " + sharedPreferences?.getString("roll_no", "")

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                root.logo.rotation = rotation
                rotation += 10
            }
        }, 100, 100)
        return root.root
    }

    override fun onDataChanged(name: String) {
        root.userName.text = "Welcome " + name
    }

    override fun onError(msg: String) {
        activity?.toast(msg)
    }

    override fun onStarted() {

    }


}

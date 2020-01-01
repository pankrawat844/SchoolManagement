package com.app.schoolmanagement.admin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.home.ui.home.AdminHomeViewModel
import com.app.schoolmanagement.admin.home.ui.home.HomeFragmentListener
import com.app.schoolmanagement.databinding.FragmentAdminHomeBinding
import com.app.schoolmanagement.students.home.ui.home.HomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class AdminHomeFragment : Fragment(), KodeinAware, HomeFragmentListener {

    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private lateinit var homeViewModel: AdminHomeViewModel
    var rotation: Float = 0.00f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val databind: FragmentAdminHomeBinding =
            DataBindingUtil.inflate(R.layout.fragment_admin_home, container, false)
        val viewomodel = ViewModelProviders.of(this, factory).get(AdminHomeViewModel::class.java)
        databind.viewmodel = viewomodel
        databind.lifecycleOwner = this
        viewomodel.view1 = activity
        viewomodel.homeFragmentListener = this


        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                databind.logo.rotation = rotation
                rotation += 10
            }

        }, 100, 100)
        return databind.root
    }

    override fun onDataChanged(name: String) {

    }

    override fun onError(msg: String) {
    }


}

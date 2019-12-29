package com.app.schoolmanagement.admin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import kotlinx.android.synthetic.main.fragment_admin_home.view.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var rotation: Float = 0.00f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_admin_home, container, false)
        val viewomodel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                root.logo.rotation = rotation
                rotation += 10
            }

        }, 100, 100)
        return root
    }

}

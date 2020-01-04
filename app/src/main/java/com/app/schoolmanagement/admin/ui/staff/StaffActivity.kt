package com.app.schoolmanagement.admin.ui.staff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.databinding.ActivityStaffBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_staff.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StaffActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    var viewModel: StaffViewModel? = null
    val factory: StaffViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databind:ActivityStaffBinding=DataBindingUtil.setContentView(this,R.layout.activity_staff)
        viewModel = ViewModelProviders.of(this, factory).get(StaffViewModel::class.java)
        viewModel?.ctx = this
        databind.viewmodel=viewModel!!
        bindUI()

    }

    private fun bindUI() {
        viewModel?.getStaff()!!.observe(this, Observer {
            //            Log.e("dataaaa",it.toString())
            initRecyclerview(it.toStaffItem())
        })
    }

    private fun initRecyclerview(toStaffItem: List<StaffItem>) {
        staff_recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(toStaffItem)
        }
        staff_recyclerview.adapter = adapter
    }


    private fun List<StaffList.Staff>.toStaffItem(): List<StaffItem> {
        return this.map {
            StaffItem(it)
        }
    }
}

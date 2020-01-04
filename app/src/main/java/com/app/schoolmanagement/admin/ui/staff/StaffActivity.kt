package com.app.schoolmanagement.admin.ui.staff

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.databinding.ActivityStaffBinding
import com.app.schoolmanagement.utils.RecyclerItemClickListenr
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_staff.*

import kotlinx.android.synthetic.main.bottomsheet_add_staff.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StaffActivity : AppCompatActivity(), KodeinAware,StaffActivityListener {

    override val kodein by kodein()
    var viewModel: StaffViewModel? = null
    val factory: StaffViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databind:ActivityStaffBinding=DataBindingUtil.setContentView(this,R.layout.activity_staff)
        viewModel = ViewModelProviders.of(this, factory).get(StaffViewModel::class.java)
        viewModel?.ctx = this
        viewModel?.getClasses()
        databind.viewmodel=viewModel!!
        bindUI()

    }

    private fun bindUI() {
        viewModel?.getStaff()!!.observe(this, Observer {
            //            Log.e("dataaaa",it.toString())
            initRecyclerview(it.toStaffItem())
        })

        staff_recyclerview.addOnItemTouchListener(RecyclerItemClickListenr(this,staff_recyclerview,object :RecyclerItemClickListenr.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {

            }

            override fun onItemLongClick(view: View?, position: Int) {
            }

        }))

        class_name.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel?.getSection(parent?.getItemAtPosition(position).toString())!!
                }
            }

        }
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

    override fun onStarted() {

    }

    override fun onSuccess(msg: String) {
    }

    override fun onError(msg: String) {
    }

    override fun onClassSuccess(it: Classes) {
        class_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, it.response!!)

    }

    override fun onSectionSuccess(it: Classes) {
        section_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, it.response!!)
    }
}

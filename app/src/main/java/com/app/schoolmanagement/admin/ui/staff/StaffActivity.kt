package com.app.schoolmanagement.admin.ui.staff

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_staff.*

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
    lateinit var bottomsheet_stafff:BottomSheetBehavior<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databind:ActivityStaffBinding=DataBindingUtil.setContentView(this,R.layout.activity_staff)
        viewModel = ViewModelProviders.of(this, factory).get(StaffViewModel::class.java)
        viewModel?.ctx = this
        viewModel?.staffActivityListener=this
        databind.viewmodel=viewModel!!
        bindUI()
        viewModel?.getClasses()

        bottomsheet_addstaff()
        add_staff.setOnClickListener {
            if (bottomsheet_stafff.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomsheet_stafff.state = BottomSheetBehavior.STATE_EXPANDED
                bottomsheet_stafff.isHideable = true
            }else
                android.widget.Toast.makeText(this@StaffActivity, "Bottomsheet open", Toast.LENGTH_SHORT).show()
        }
//        buttonOk.setOnClickListener {
//            viewModel
//            viewModel?.onAddStaff(it)
//        }
    }

    private fun bindUI() {
        viewModel?.getStaff()!!.observe(this, Observer {
            //            Log.e("dataaaa",it.toString())
            initRecyclerview(it.toStaffItem())
        })

        staff_recyclerview.addOnItemTouchListener(RecyclerItemClickListenr(this,staff_recyclerview,object :RecyclerItemClickListenr.OnItemClickListener{
            override fun onItemClick(view: View, position: Int)
            {

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
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel?.selected_class=parent?.getItemAtPosition(position).toString()
                    viewModel?.getSection(parent?.getItemAtPosition(position).toString())!!
                }
            }

        }

        section_name.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel?.selected_section=parent?.getItemAtPosition(position).toString()
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
            ArrayAdapter(this, android.R.layout.simple_list_item_1, it.response!!.toItem())

    }

    override fun onSectionSuccess(it: Classes) {
        section_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, it.response?.toSectinItem()!!)
    }


    private fun bottomsheet_addstaff() {
        bottomsheet_stafff = BottomSheetBehavior.from(bottomsheet_staff)
        bottomsheet_stafff.state = BottomSheetBehavior.STATE_HIDDEN
        bottomsheet_stafff.bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {

            }

            override fun onStateChanged(p0: View, p1: Int) {
            }

        }


    }

    private fun List<Classes.Data>.toItem(): List<String> {
        return this.map {
            it.className!!
        }
    }

    private fun List<Classes.Data>.toSectinItem(): List<String> {
        return this.map {
            it.sectionName!!
        }
    }
}

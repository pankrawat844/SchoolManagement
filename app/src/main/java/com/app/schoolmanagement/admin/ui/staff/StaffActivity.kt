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
import com.app.schoolmanagement.students.utils.RecyclerItemClickListenr
import com.app.schoolmanagement.students.utils.hide
import com.app.schoolmanagement.students.utils.show
import com.app.schoolmanagement.students.utils.toast
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
    var bottomsheet_stafff: BottomSheetBehavior<View>? = null
    var class_list: List<Classes.Data>? = ArrayList<Classes.Data>()
    var section_list: List<Classes.Data>? = ArrayList<Classes.Data>()
    var staff_list: List<StaffList.Staff>? = ArrayList<StaffList.Staff>()

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
            if (bottomsheet_stafff == null)
                bottomsheet_addstaff()
            if (bottomsheet_stafff?.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomsheet_stafff?.state = BottomSheetBehavior.STATE_EXPANDED
                bottomsheet_stafff?.isHideable = true
            }else
                Toast.makeText(this@StaffActivity, "Bottomsheet open", Toast.LENGTH_SHORT).show()
        }
//        buttonOk.setOnClickListener {
//            viewModel
//            viewModel?.onAddStaff(it)
//        }
    }

    private fun bindUI() {
        viewModel?.getStaff()!!.observe(this, Observer {
            //            Log.e("dataaaa",it.toString())
            staff_list = it
            initRecyclerview(it.toStaffItem())
        })

        staff_recyclerview.addOnItemTouchListener(RecyclerItemClickListenr(this,staff_recyclerview,object :RecyclerItemClickListenr.OnItemClickListener{
            override fun onItemClick(view: View, position: Int)
            {
                if (bottomsheet_stafff == null)
                    bottomsheet_addstaff()
                class_name.setSelection(class_list?.toItem()?.indexOf(staff_list?.get(position)?.className)!!)
                section_name.setSelection(section_list?.toItem()?.indexOf(staff_list?.get(position)?.sectionName)!!)
                id.setText(staff_list?.get(position)?.userid)
                password.setText(staff_list?.get(position)?.password)
                incharge.isChecked = staff_list?.get(position)?.isIncharge == "1"
                if (bottomsheet_stafff?.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomsheet_stafff?.state = BottomSheetBehavior.STATE_EXPANDED
                }
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
        progress_bar.show()
    }

    override fun onSuccess(msg: String) {
        progress_bar.hide()
        toast(msg)

        if (bottomsheet_stafff?.state == BottomSheetBehavior.STATE_EXPANDED)
        {
            bottomsheet_stafff?.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    override fun onError(msg: String) {
        progress_bar.hide()
        toast(msg)
    }

    override fun onClassSuccess(it: Classes) {
        class_list = it.response
        class_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, it.response!!.toItem())

    }

    override fun onSectionSuccess(it: Classes) {
        section_list = it.response
        section_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, it.response?.toSectinItem()!!)
    }


    private fun bottomsheet_addstaff() {
        bottomsheet_stafff = BottomSheetBehavior.from(bottomsheet_staff)
        bottomsheet_stafff?.state = BottomSheetBehavior.STATE_HIDDEN
        bottomsheet_stafff?.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {

            }

            override fun onStateChanged(p0: View, p1: Int) {
                if (p1 == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomsheet_stafff = null
                }
            }


            })


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

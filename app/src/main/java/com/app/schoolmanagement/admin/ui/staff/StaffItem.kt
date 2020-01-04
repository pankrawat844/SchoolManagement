package com.app.schoolmanagement.admin.ui.staff

import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.databinding.ItemStaffBinding
import com.xwray.groupie.databinding.BindableItem

class StaffItem(var data: StaffList.Staff) : BindableItem<ItemStaffBinding>() {
    override fun getLayout() = R.layout.item_staff

    override fun bind(viewBinding: ItemStaffBinding, position: Int) {
        viewBinding.data = data
    }

}
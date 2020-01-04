package com.app.schoolmanagement.admin.ui.staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminRepository

class StaffViewModelFactory(val adminRepository: AdminRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StaffViewModel(adminRepository) as T
    }
}
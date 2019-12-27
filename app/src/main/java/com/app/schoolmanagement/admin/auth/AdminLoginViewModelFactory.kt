package com.app.schoolmanagement.admin.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminLoginRepository
import com.app.schoolmanagement.students.repositories.SchoolLoginRepository

class AdminLoginViewModelFactory(val adminLoginRepository: AdminLoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AdminLoginViewModel(
            adminLoginRepository
        ) as T
    }
}
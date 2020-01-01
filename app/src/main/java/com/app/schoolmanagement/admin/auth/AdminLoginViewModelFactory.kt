package com.app.schoolmanagement.admin.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.repositories.AdminRepository

class AdminLoginViewModelFactory(val adminLoginRepository: AdminRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AdminLoginViewModel(
            adminLoginRepository
        ) as T
    }
}
package com.app.schoolmanagement.students.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.admin.home.ui.home.AdminHomeViewModel
import com.app.schoolmanagement.admin.repositories.AdminRepository
import com.app.schoolmanagement.students.repositories.StudentRepository

class AdminHomeViewModelFactory(val studentRepository: AdminRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AdminHomeViewModel(
            studentRepository
        ) as T
    }
}
package com.app.schoolmanagement.students.auth.schoollogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagement.students.repositories.SchoolLoginRepository

class SchoolLoginVIewModelFactory(val schoolLoginRepository: SchoolLoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SchoolLoginViewModel(
            schoolLoginRepository
        ) as T
    }
}